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

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.ExprTypes;
import org.kayura.uasp.workflow.FormFlowPayload;
import org.kayura.uasp.workflow.cmd.UpdateFormFlowCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateFormFlowCommandHandler implements CommandHandler<UpdateFormFlowCommand, HttpResult> {

  private final FormFlowManager flowManager;

  public UpdateFormFlowCommandHandler(FormFlowManager flowManager) {
    this.flowManager = flowManager;
  }

  @Transactional
  public HttpResult execute(UpdateFormFlowCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FormFlowPayload payload = command.getPayload();
    String flowId = Optional.ofNullable(command.getFlowId()).orElse(payload.getFlowId());

    FormFlowEntity entity = flowManager.selectById(flowId);
    if (entity == null) {
      return HttpResult.error("业务流程ID不存在。");
    }

    flowManager.autoPatchPrimary(Boolean.TRUE.equals(payload.getPrimary()), flowId);

    entity.setDisplayName(payload.getDisplayName());
    entity.setDescription(payload.getDescription());
    entity.setPrimary(payload.getPrimary());
    if (ExprTypes.CUSTOM.equals(payload.getExprType())) {
      entity.setExprType(payload.getExprType());
    }
    if (ExprTypes.INNER.equals(payload.getExprType())) {
      entity.setCustomExpr(payload.getCustomExpr());
    }
    entity.setInnerExpr(payload.getInnerExpr());
    entity.setFlowLabels(flowManager.sortedFlowLabels(payload.getFlowLabels()));
    entity.setStatus(payload.getStatus());
    flowManager.updateById(flowId, entity);

    return HttpResult.ok();
  }

}
