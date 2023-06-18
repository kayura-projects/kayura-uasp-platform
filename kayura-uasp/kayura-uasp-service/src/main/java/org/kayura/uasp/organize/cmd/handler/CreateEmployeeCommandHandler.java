/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.nextid.RandomIdGenerator;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.organize.EmployeePayload;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.JobStatus;
import org.kayura.uasp.organize.WorkStatus;
import org.kayura.uasp.organize.cmd.CreateEmployeeCommand;
import org.kayura.uasp.organize.entity.*;
import org.kayura.uasp.organize.manage.*;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateEmployeeCommandHandler implements CommandHandler<CreateEmployeeCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final IdentityManager identityManager;
  private final EmployeeManager employeeManager;
  private final UserManager userManager;
  private final CompanyManager companyManager;
  private final DepartManager departManager;
  private final PositionManager positionManager;
  private final PasswordEncoder passwordEncoder;

  public CreateEmployeeCommandHandler(ModelMapper modelMapper,
                                      IdentityManager identityManager,
                                      EmployeeManager employeeManager,
                                      UserManager userManager,
                                      CompanyManager companyManager,
                                      DepartManager departManager,
                                      PositionManager positionManager,
                                      PasswordEncoder passwordEncoder) {
    this.modelMapper = modelMapper;
    this.identityManager = identityManager;
    this.employeeManager = employeeManager;
    this.userManager = userManager;
    this.companyManager = companyManager;
    this.departManager = departManager;
    this.positionManager = positionManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public HttpResult execute(CreateEmployeeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    EmployeePayload payload = command.getPayload();

    // pretreatment
    Result result = this.createPretreatment(payload);
    if (result.isFail()) {
      return HttpResult.of(result);
    }

    // user
    this.autoCreateUser(payload, loginUser);

    // employee
    EmployeeEntity employee = modelMapper.map(payload, EmployeeEntity.class);
    employee.setCreatorId(loginUser.getUserId());
    employee.setCreateTime(DateUtils.now());
    employeeManager.insertOne(employee);

    // identity
    this.autoCreateIdentity(payload);

    EmployeeQuery model = modelMapper.map(employee, EmployeeQuery.class);
    return HttpResult.okBody(model);
  }

  protected Result createPretreatment(EmployeePayload payload) {

    // set new id
    payload.setEmployeeId(employeeManager.nextId());

    // 检查数据约束
    if (StringUtils.isAllBlank(payload.getCompanyId(), payload.getDepartId(), payload.getPositionId())) {
      return Result.fail("必须指定所属组织。");
    }

    // resolve
    if (StringUtils.isNotBlank(payload.getPositionId())) {
      PositionEntity position = positionManager.selectOne(w -> {
        w.eq(PositionEntity::getPositionId, payload.getPositionId());
      });
      if (position == null) {
        return Result.fail("指定的岗位不存在。");
      }
      payload.setTenantId(position.getTenantId());
      payload.setCompanyId(position.getCompanyId());
      payload.setDepartId(position.getDepartId());
    } else if (StringUtils.isNotBlank(payload.getDepartId())) {
      DepartEntity depart = departManager.selectOne(w -> {
        w.select(DepartEntity::getCompanyId);
        w.select(DepartEntity::getTenantId);
        w.eq(DepartEntity::getDepartId, payload.getDepartId());
      });
      if (depart == null) {
        return Result.fail("指定的部门不存在。");
      }
      payload.setCompanyId(depart.getCompanyId());
      payload.setTenantId(depart.getTenantId());
    } else if (StringUtils.isNotBlank(payload.getCompanyId())) {
      CompanyEntity company = companyManager.selectOne(w -> {
        w.eq(CompanyEntity::getCompanyId, payload.getCompanyId());
      });
      if (company == null) {
        return Result.fail("指定的公司不存在。");
      }
      payload.setTenantId(company.getTenantId());
    }

    // userName
    if (StringUtils.hasText(payload.getUserName())) {
      if (userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getUserName())) > 0) {
        return Result.fail("该账号已被注册。");
      }
    } else {
      payload.setUserName(RandomIdGenerator.INSTANCE.nextId());
    }

    // mobile
    if (StringUtils.hasText(payload.getMobile()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getMobile, payload.getMobile())) > 0) {
      return Result.fail("该手机号已被占用。");
    }

    // email
    if (StringUtils.hasText(payload.getEmail()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getEmail, payload.getEmail())) > 0) {
      return Result.fail("该邮箱已被占用。");
    }

    // jobName
    if (StringUtils.hasText(payload.getJobNo()) &&
      employeeManager.selectCount(w -> {
        w.eq(EmployeeEntity::getJobNo, payload.getJobNo());
        w.eq(EmployeeEntity::getCompanyId, payload.getCompanyId());
      }) > 0) {
      return Result.fail("该工号已被占用。");
    }

    // status
    if (payload.getStatus() == null) {
      payload.setStatus(WorkStatus.Active);
    }

    return Result.ok();
  }

  protected void autoCreateIdentity(EmployeePayload payload) {

    if (StringUtils.hasText(payload.getPositionId())) {
      IdentityEntity identity = IdentityEntity.create()
        .setIdentityId(identityManager.nextId())
        .setEmployeeId(payload.getEmployeeId())
        .setDepartId(payload.getDepartId())
        .setPositionId(payload.getPositionId())
        .setStatus(JobStatus.Active);
      identityManager.insertOne(identity);
    }
  }

  protected void autoCreateUser(EmployeePayload payload, LoginUser loginUser) {

    if (StringUtils.isBlank(payload.getPassword())) {
      payload.setPassword(RandomStringUtils.randomAlphabetic(16));
    }

    UserEntity user = UserEntity.create()
      .setUserId(payload.getEmployeeId())
      .setUserName(payload.getJobNo())
      .setDisplayName(payload.getRealName())
      .setTenantId(payload.getTenantId())
      .setMobile(payload.getMobile())
      .setEmail(payload.getEmail())
      .setPassword(this.passwordEncoder.encode(payload.getPassword()))
      .setAvatar(payload.getAvatar())
      .setUserType(Optional.ofNullable(payload.getUserType()).orElse(UserTypes.USER))
      .setDisplayName(payload.getRealName())
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setEnabled(Optional.ofNullable(payload.getEnabled()).orElse(Boolean.TRUE))
      .setLocked(Optional.ofNullable(payload.getLocked()).orElse(Boolean.FALSE));
    userManager.insertOne(user);
  }
}
