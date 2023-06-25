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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.SelectItem;
import org.kayura.type.TreeNode;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.tenant.TenantVo;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    boolean formApp = command.isFormApp();
    String appId = command.getAppId();

    List<TenantEntity> entities;

    // 前端用户仅能选当前租户
    if (loginUser != null && loginUser.hasTenantUser()) {
      entities = List.of(
        this.tenantManager.selectById(loginUser.getTenantId())
      );
    } else {
      if (formApp) {
        entities = this.chooseTenantsFromApp(command);
      } else {
        entities = this.chooseValidTenants();
      }
    }

    return outputResult(command, entities);
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

    String appId = command.getAppId();
    boolean includeApplic = command.isIncludeApplic();

    List<CompanyApplicEntity> entities = companyApplicManager.selectList(w -> {
      if (StringUtils.hasText(appId)) {
        w.eq(CompanyApplicEntity::getAppId, appId);
      }
      w.eq(CompanyApplicEntity::getCompanyType, CompanyTypes.Tenant);
      w.eq(CompanyApplicEntity::getStatus, DataStatus.Valid);
    });

    List<TenantEntity> tenantEntities = new ArrayList<>();
    List<String> tenantIds = entities.stream().map(CompanyApplicEntity::getTenantId).toList();
    if (CollectionUtils.isNotEmpty(tenantIds)) {
      for (String tenantId : tenantIds) {
        List<CompanyApplicEntity> list = entities.stream().filter(x -> x.getTenantId().equals(tenantId)).toList();
        if (!list.isEmpty()) {
          TenantEntity tenant = TenantEntity.create()
            .setTenantId(tenantId)
            .setCode(list.get(0).getTenantCode())
            .setName(list.get(0).getTenantName());
          if (includeApplic) {
            List<ApplicEntity> applics = list.stream().map(m ->
              ApplicEntity.create()
                .setAppId(m.getAppId())
                .setCode(m.getAppCode())
                .setName(m.getAppName())
            ).toList();
            tenant.setApplics(applics);
          }
          tenantEntities.add(tenant);
        }
      }
    }
    return tenantEntities;
  }

  private HttpResult outputResult(ChooseTenantCommand command, List<TenantEntity> entities) {

    OutputTypes output = command.getOutput();

    if (OutputTypes.TREE.equals(output)) {
      List<TreeNode> nodes = entities.stream().map(m -> {
        TreeNode node = TreeNode.create().setId(m.getTenantId()).setCode(m.getCode()).setText(m.getName());
        if (CollectionUtils.isNotEmpty(m.getApplics())) {
          List<ApplicVo> applics = m.getApplics().stream().map(m1 ->
            ApplicVo.create().setAppId(m1.getAppId()).setCode(m1.getCode()).setName(m1.getName())
          ).toList();
          node.put("APPLICS", applics);
        }
        return node;
      }).collect(Collectors.toList());
      return HttpResult.okBody(nodes);
    } else if (OutputTypes.SELECT.equals(output)) {
      List<SelectItem> selectItems = entities.stream().map(m -> {
        SelectItem node = SelectItem.create().setId(m.getTenantId()).setCode(m.getCode()).setText(m.getName());
        if (CollectionUtils.isNotEmpty(m.getApplics())) {
          List<ApplicVo> applics = m.getApplics().stream().map(m1 ->
            ApplicVo.create().setAppId(m1.getAppId()).setCode(m1.getCode()).setName(m1.getName())
          ).toList();
          node.put("APPLICS", applics);
        }
        return node;
      }).collect(Collectors.toList());
      return HttpResult.okBody(selectItems);
    } else {
      List<TenantVo> selectItems = entities.stream()
        .map(m -> modelMapper.map(m, TenantVo.class))
        .collect(Collectors.toList());
      return HttpResult.okBody(selectItems);
    }
  }

}
