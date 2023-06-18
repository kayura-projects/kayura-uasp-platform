/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
