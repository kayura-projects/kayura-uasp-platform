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

package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.cmd.CommandHandler;
import org.kayura.except.ExceptUtils;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.ExprTypes;
import org.kayura.uasp.workflow.FormFlowPayload;
import org.kayura.uasp.workflow.FormFlowVo;
import org.kayura.uasp.workflow.cmd.CreateFormFlowCommand;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class CreateFormFlowCommandHandler
  implements CommandHandler<CreateFormFlowCommand, HttpResult>, UaspBpmnConstants {

  private final FormDefineManager defineManager;
  private final FormFlowManager flowManager;
  private final RepositoryService repositoryService;
  private final ModelMapper modelMapper;

  public CreateFormFlowCommandHandler(FormDefineManager defineManager,
                                      FormFlowManager flowManager,
                                      ModelMapper modelMapper,
                                      RepositoryService repositoryService) {
    this.defineManager = defineManager;
    this.flowManager = flowManager;
    this.repositoryService = repositoryService;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateFormFlowCommand command) {

    FormFlowPayload payload = command.getPayload();

    // 流程代码
    if (flowManager.selectCount(w ->
      w.eq(FormFlowEntity::getProcessKey, payload.getProcessKey())
    ) > 0) {
      ExceptUtils.business("流程KEY " + payload.getProcessKey() + " 已经被占用。");
    }

    // 流程名称
    if (flowManager.selectCount(w -> {
      w.eq(FormFlowEntity::getFormId, payload.getFormId());
      w.eq(FormFlowEntity::getTenantId, payload.getTenantId());
      w.eq(FormFlowEntity::getDisplayName, payload.getDisplayName());
    }) > 0) {
      ExceptUtils.business("请使用不同的流程名称。");
    }

    flowManager.autoPatchPrimary(Boolean.TRUE.equals(payload.getPrimary()), payload.getFormId());

    FormFlowEntity entity = FormFlowEntity.create()
      .setFlowId(flowManager.nextId())
      .setFormId(payload.getFormId())
      .setTenantId(payload.getTenantId())
      .setProcessKey(payload.getProcessKey())
      .setDisplayName(payload.getDisplayName())
      .setDescription(payload.getDescription())
      .setPrimary(Optional.ofNullable(payload.getPrimary()).orElse(Boolean.FALSE))
      .setExprType(Optional.ofNullable(payload.getExprType()).orElse(ExprTypes.NONE))
      .setFlowLabels(payload.getFlowLabels())
      .setStatus(Optional.ofNullable(payload.getStatus()).orElse(DataStatus.Valid));
    if (ExprTypes.CUSTOM.equals(entity.getExprType())) {
      entity.setCustomExpr(payload.getCustomExpr());
    }
    if (ExprTypes.INNER.equals(entity.getExprType())) {
      entity.setInnerExpr(payload.getInnerExpr());
    }
    flowManager.insertOne(entity);

    // 创建默认流程定义
    this.createBpmnModel(payload);

    FormFlowVo model = modelMapper.map(entity, FormFlowVo.class);
    return HttpResult.okBody(model);
  }

  protected void createBpmnModel(FormFlowPayload payload) {

    FormDefineEntity formDefine = defineManager.selectById(payload.getFormId());

    Model model = repositoryService.newModel();
    model.setCategory(formDefine.getCode());
    model.setName(payload.getDisplayName());
    model.setKey(payload.getProcessKey());
    model.setTenantId(payload.getTenantId());

    // 初始流程定义
    String XML = String.format(INITIAL_DIAGRAM, payload.getProcessKey(), payload.getProcessKey());

    repositoryService.saveModel(model);
    repositoryService.addModelEditorSource(model.getId(), XML.getBytes(StandardCharsets.UTF_8));
  }
}
