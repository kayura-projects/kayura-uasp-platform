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
