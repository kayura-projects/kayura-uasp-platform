/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.SelectItem;
import org.kayura.type.TreeNode;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.tenant.TenantVo;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChooseTenantCommandHandler implements CommandHandler<ChooseTenantCommand, HttpResult> {

  private final TenantManager tenantManager;
  private final CompanyApplicManager companyApplicManager;
  private final ModelMapper modelMapper;

  public ChooseTenantCommandHandler(TenantManager tenantManager,
                                    CompanyApplicManager companyApplicManager,
                                    ModelMapper modelMapper) {
    this.tenantManager = tenantManager;
    this.companyApplicManager = companyApplicManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseTenantCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OutputTypes output = command.getOutType();

    List<TenantEntity> entities;

    // 前端用户仅能选当前租户
    if (loginUser != null && loginUser.hasTenantUser()) {
      entities = List.of(
        this.tenantManager.selectById(loginUser.getTenantId())
      );
    } else {
      if (Boolean.TRUE.equals(command.isHasApp())) {
        entities = this.chooseTenantsFromApp(command);
      } else {
        entities = this.chooseValidTenants();
      }
    }

    return outputResult(output, entities);
  }

  private List<TenantEntity> chooseValidTenants() {

    List<TenantEntity> entities = tenantManager.selectList(w -> {
      w.select(TenantEntity::getTenantId);
      w.select(TenantEntity::getCode);
      w.select(TenantEntity::getName);
      w.eq(TenantEntity::getStatus, DataStatus.Valid);
    });
    return entities;
  }

  @NotNull
  private List<TenantEntity> chooseTenantsFromApp(ChooseTenantCommand command) {

    List<TenantEntity> entities = companyApplicManager.selectList(w -> {
      w.distinct();
      w.select(CompanyApplicEntity::getCompanyId);
      w.select(CompanyApplicEntity::getCompanyCode);
      w.select(CompanyApplicEntity::getCompanyName);
      if (StringUtils.hasText(command.getAppId())) {
        w.eq(CompanyApplicEntity::getAppId, command.getAppId());
      }
      w.eq(CompanyApplicEntity::getCompanyType, CompanyTypes.Tenant);
      w.eq(CompanyApplicEntity::getStatus, DataStatus.Valid);
    }).stream().map(m ->
      TenantEntity.create().setTenantId(m.getCompanyId()).setCode(m.getCompanyCode()).setName(m.getCompanyName())
    ).toList();
    return entities;
  }

  private HttpResult outputResult(OutputTypes output, List<TenantEntity> entities) {

    if (OutputTypes.TREE.equals(output)) {
      List<TreeNode> nodes = entities.stream().map(m ->
        TreeNode.create().setId(m.getTenantId()).setCode(m.getCode()).setText(m.getName())
      ).collect(Collectors.toList());
      return HttpResult.okBody(nodes);
    } else if (OutputTypes.SELECT.equals(output)) {
      List<SelectItem> selectItems = entities.stream().map(m ->
        SelectItem.create().setId(m.getTenantId()).setCode(m.getCode()).setText(m.getName())
      ).collect(Collectors.toList());
      return HttpResult.okBody(selectItems);
    } else {
      List<TenantVo> selectItems = entities.stream()
        .map(m -> modelMapper.map(m, TenantVo.class))
        .collect(Collectors.toList());
      return HttpResult.okBody(selectItems);
    }
  }

}
