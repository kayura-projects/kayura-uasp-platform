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
