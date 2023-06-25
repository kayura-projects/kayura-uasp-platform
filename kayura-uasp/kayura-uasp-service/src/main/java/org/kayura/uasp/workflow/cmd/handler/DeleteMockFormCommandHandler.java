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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.ApproveStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.activiti.manage.ActivitiManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.workflow.cmd.DeleteMockFormCommand;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteMockFormCommandHandler implements CommandHandler<DeleteMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ActivitiManager activitiManager;

  public DeleteMockFormCommandHandler(MockFormManager mockFormManager,
                                      ActivitiManager activitiManager) {
    this.mockFormManager = mockFormManager;
    this.activitiManager = activitiManager;
  }

  @Transactional
  public HttpResult execute(DeleteMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String mockId = Optional.ofNullable(command.getMockId()).orElse(payload.getId());

    List<MockFormEntity> entities = null;
    if (StringUtils.hasText(mockId)) {
      entities = mockFormManager.selectList(w -> w.eq(MockFormEntity::getMockId, mockId));
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      entities = mockFormManager.selectList(w -> w.in(MockFormEntity::getMockId, payload.getIds()));
    }

    // 如果启动了流程，还需要将流程一并删除.
    if (entities != null) {
      for (MockFormEntity entity : entities) {
        if (!ApproveStatus.Draft.equals(entity.getStatus())) {
          activitiManager.deleteProcessByBusinessKey(entity.getMockId());
        }
        mockFormManager.deleteById(entity.getMockId());
      }
    }

    return HttpResult.ok();
  }
}
