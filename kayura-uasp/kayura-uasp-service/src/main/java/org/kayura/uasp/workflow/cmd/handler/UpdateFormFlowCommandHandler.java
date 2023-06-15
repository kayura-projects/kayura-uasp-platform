/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
