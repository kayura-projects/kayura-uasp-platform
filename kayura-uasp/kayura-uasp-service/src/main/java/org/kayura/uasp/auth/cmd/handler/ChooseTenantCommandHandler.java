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

    OutputTypes output = command.getOutType();

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
