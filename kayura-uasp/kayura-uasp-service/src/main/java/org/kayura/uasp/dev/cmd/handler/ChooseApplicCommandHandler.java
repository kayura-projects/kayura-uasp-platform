/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
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
import org.kayura.utils.CollectionUtils;
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

    LoginUser loginUser = command.getLoginUser();
    OutputTypes output = command.getOutput();
    ApplicTypes applicType = command.getType();
    String tenantId = command.getTenantId();
    String companyId = command.getCompanyId();
    String userId = command.getUserId();
    List<String> exclusionIds = command.getExclusionIds();

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
      entities = this.chooseValidApps(applicType, exclusionIds);
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

  private List<ApplicEntity> chooseValidApps(ApplicTypes applicType, List<String> exclusionIds) {

    List<ApplicEntity> entities = applicManager.selectList(w -> {
      w.select(ApplicEntity::getAppId);
      w.select(ApplicEntity::getName);
      w.eq(ApplicEntity::getStatus, DataStatus.Valid);
      if (applicType != null) {
        w.eq(ApplicEntity::getType, applicType);
      }
      if (CollectionUtils.isNotEmpty(exclusionIds)) {
        w.notIn(ApplicEntity::getAppId, exclusionIds);
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
