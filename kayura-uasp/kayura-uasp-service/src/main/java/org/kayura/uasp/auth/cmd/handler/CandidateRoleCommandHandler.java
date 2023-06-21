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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.SelectItem;
import org.kayura.type.UsableStatus;
import org.kayura.uasp.auth.cmd.CandidateRoleCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.role.RoleVo;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConsts;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CandidateRoleCommandHandler implements CommandHandler<CandidateRoleCommand, HttpResult> {

  private final RoleUserManager roleUserManager;
  private final EmployeeManager employeeManager;
  private final CompanyApplicManager companyApplicManager;
  private final RoleManager roleManager;
  private final ModelMapper modelMapper;

  public CandidateRoleCommandHandler(RoleUserManager roleUserManager,
                                     EmployeeManager employeeManager,
                                     CompanyApplicManager companyApplicManager,
                                     RoleManager roleManager,
                                     ModelMapper modelMapper) {
    this.roleUserManager = roleUserManager;
    this.employeeManager = employeeManager;
    this.companyApplicManager = companyApplicManager;
    this.roleManager = roleManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(CandidateRoleCommand command) {

    OutputTypes output = command.getOutput();
    RoleTypes roleType = command.getRoleType();
    String userId = command.getUserId();
    String appId = command.getAppId();
    String tenantId = command.getTenantId();

    if (StringUtils.isBlank(userId)) {
      return HttpResult.error("必需提供 userId 值。");
    }

    Set<String> appIds;

    // 为用户提供候选角色时，可以排除掉该员工已经拥有的角色
    Set<String> exclusions = roleUserManager.selectList(w -> {
      w.select(RoleUserEntity::getRoleId);
      w.eq(RoleUserEntity::getUserId, userId);
    }).stream().map(RoleUserEntity::getRoleId).collect(Collectors.toSet());

    EmployeeEntity employee = employeeManager.selectOne(w -> {
      w.select(EmployeeEntity::getEmployeeId);
      w.select(EmployeeEntity::getCompanyId);
      w.select(EmployeeEntity::getTenantId);
      w.eq(EmployeeEntity::getEmployeeId, userId);
    });
    if (employee != null) {

      // 如果该用户是员工，还应包括“租户角色”与“应用角色”。
      tenantId = employee.getTenantId();
      // 没有指定应用，将从员所属租户中去获取注册的应用。
      if (StringUtils.isBlank(appId)) {
        appIds = companyApplicManager.selectList(w -> {
          w.select(CompanyApplicEntity::getAppId);
          w.eq(CompanyApplicEntity::getTenantId, employee.getTenantId());
        }).stream().map(CompanyApplicEntity::getAppId).collect(Collectors.toSet());
      } else {
        appIds = Set.of(appId);
      }

    } else {
      // 如果不是员工(即为后台管理员),将允许UASP应用角色。
      appIds = Set.of(UaspConsts.UASP_APP_ID);
    }

    List<RoleEntity> entities = new ArrayList<>();
    List<RoleEntity> roles1 = this.selectRoleByApp(appIds, roleType);
    if (!roles1.isEmpty()) {
      entities.addAll(roles1);
    }
    if (StringUtils.hasText(tenantId)) {
      List<RoleEntity> roles2 = this.selectRoleByTenant(tenantId, roleType);
      if (!roles2.isEmpty()) {
        entities.addAll(roles2);
      }
    }

    if (!entities.isEmpty() && !exclusions.isEmpty()) {
      entities = entities.stream().filter(x -> !exclusions.contains(x.getRoleId())).toList();
    }

    if (OutputTypes.SELECT.equals(output)) {
      List<SelectItem> selectItems = entities.stream().map(m ->
        SelectItem.create()
          .setId(m.getRoleId())
          .setCode(m.getCode())
          .setText(m.getName())
          .put("app", m.getAppName())
          .put("tenant", m.getTenantName())
      ).collect(Collectors.toList());
      return HttpResult.okBody(selectItems);
    } else {
      List<RoleVo> selectItems = entities.stream()
        .map(m -> modelMapper.map(m, RoleVo.class))
        .collect(Collectors.toList());
      return HttpResult.okBody(selectItems);
    }

  }

  protected List<RoleEntity> selectRoleByApp(Set<String> appIds, RoleTypes roleType) {

    List<RoleEntity> entities = roleManager.selectList(w -> {
      w.select(RoleEntity::getRoleId);
      w.select(RoleEntity::getCode);
      w.select(RoleEntity::getName);
      w.select(RoleEntity::getAppName);
      w.select(RoleEntity::getTenantName);
      w.in(RoleEntity::getAppId, appIds);
      w.isNull(RoleEntity::getTenantId);
      w.eq(RoleEntity::getType, roleType);
      w.eq(RoleEntity::getStatus, UsableStatus.Valid);
    });
    return entities;
  }

  protected List<RoleEntity> selectRoleByTenant(String tenantId, RoleTypes roleType) {

    List<RoleEntity> entities = roleManager.selectList(w -> {
      w.select(RoleEntity::getRoleId);
      w.select(RoleEntity::getCode);
      w.select(RoleEntity::getName);
      w.select(RoleEntity::getAppName);
      w.select(RoleEntity::getTenantName);
      w.eq(RoleEntity::getTenantId, tenantId);
      w.eq(RoleEntity::getType, roleType);
      w.eq(RoleEntity::getStatus, UsableStatus.Valid);
    });
    return entities;
  }
}
