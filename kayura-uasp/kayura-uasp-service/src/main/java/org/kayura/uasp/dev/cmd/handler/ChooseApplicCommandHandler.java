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

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.*;
import org.kayura.uasp.applic.ApplicTypes;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ChooseApplicCommandHandler implements CommandHandler<ChooseApplicCommand, HttpResult> {

  private final EmployeeManager employeeManager;
  private final ApplicManager applicManager;
  private final CompanyApplicManager companyApplicManager;
  private final ModelMapper modelMapper;

  public ChooseApplicCommandHandler(EmployeeManager employeeManager,
                                    ApplicManager applicManager,
                                    CompanyApplicManager companyApplicManager,
                                    ModelMapper modelMapper) {
    this.employeeManager = employeeManager;
    this.applicManager = applicManager;
    this.companyApplicManager = companyApplicManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseApplicCommand command) {

    OutputTypes output = command.getOutput();
    ApplicTypes applicType = command.getType();
    String tenantId = command.getTenantId();
    String companyId = command.getCompanyId();
    String userId = command.getUserId();
    Integer level = command.getLevel();
    boolean notUasp = command.isNotUasp();

    // 若指定的是userId将从employee信息中提取.
    if (StringUtils.hasText(userId)) {
      EmployeeEntity employee = employeeManager.selectOne(w -> w.eq(EmployeeEntity::getEmployeeId, userId));
      if (employee != null) {
        if (Objects.equals(employee.getTenantId(), employee.getCompanyId())) {
          tenantId = employee.getTenantId();
        } else {
          companyId = employee.getCompanyId();
        }
      }
    }

    List<ApplicEntity> entities;
    if (StringUtils.isNotBlank(tenantId)) {
      entities = this.chooseAppsByTenant(tenantId);
    } else if (StringUtils.hasText(companyId)) {
      entities = this.chooseAppsByCompany(companyId);
    } else {
      entities = this.chooseValidApps(applicType, notUasp);
    }

    return outputResult(output, entities);
  }

  @NotNull
  private List<ApplicEntity> chooseAppsByCompany(String companyId) {

    List<ApplicEntity> entities = companyApplicManager.selectList(w -> {
      w.select(CompanyApplicEntity::getAppId);
      w.select(CompanyApplicEntity::getAppCode);
      w.select(CompanyApplicEntity::getAppName);
      w.eq(CompanyApplicEntity::getCompanyId, companyId);
      w.eq(CompanyApplicEntity::getStatus, UsableStatus.Valid);
      w.orderByAsc(CompanyApplicEntity::getAppSort);
    }).stream().map(m ->
      ApplicEntity.create().setAppId(m.getAppId()).setCode(m.getAppCode()).setName(m.getAppName())
    ).collect(Collectors.toList());
    return entities;
  }

  private List<ApplicEntity> chooseAppsByTenant(String tenantId) {

    List<ApplicEntity> entities = companyApplicManager.selectList(w -> {
      w.select(CompanyApplicEntity::getAppId);
      w.select(CompanyApplicEntity::getAppCode);
      w.select(CompanyApplicEntity::getAppName);
      w.eq(CompanyApplicEntity::getTenantId, tenantId);
      w.eq(CompanyApplicEntity::getStatus, UsableStatus.Valid);
      w.orderByAsc(CompanyApplicEntity::getAppSort);
    }).stream().map(m ->
      ApplicEntity.create().setAppId(m.getAppId()).setCode(m.getAppCode()).setName(m.getAppName())
    ).toList();
    return entities;
  }

  private List<ApplicEntity> chooseValidApps(ApplicTypes applicType, boolean notUasp) {

    List<ApplicEntity> entities = applicManager.selectList(w -> {
      w.select(ApplicEntity::getAppId);
      w.select(ApplicEntity::getName);
      if (notUasp) {
        w.notEq(ApplicEntity::getAppId, UaspConstants.UASP_APP_ID);
      }
      w.eq(ApplicEntity::getStatus, DataStatus.Valid);
      if (applicType != null) {
        w.eq(ApplicEntity::getType, applicType);
      }
    });
    return entities;
  }

  private HttpResult outputResult(OutputTypes output, List<ApplicEntity> entities) {

    if (OutputTypes.TREE.equals(output)) {
      List<TreeNode> collect = entities.stream()
        .map(m -> TreeNode.create().setId(m.getAppId()).setText(m.getName()))
        .collect(Collectors.toList());
      return HttpResult.okBody(collect);
    } else if (OutputTypes.SELECT.equals(output)) {
      List<SelectItem> collect = entities.stream()
        .map(m -> SelectItem.create().setId(m.getAppId()).setText(m.getName()))
        .collect(Collectors.toList());
      return HttpResult.okBody(collect);
    } else {
      List<ApplicVo> collect = entities.stream()
        .map(m -> modelMapper.map(m, ApplicVo.class))
        .collect(Collectors.toList());
      return HttpResult.okBody(collect);
    }
  }

}
