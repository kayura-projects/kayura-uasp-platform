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
import org.kayura.uasp.mockform.MockFormPayload;
import org.kayura.uasp.workflow.cmd.UpdateMockFormCommand;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateMockFormCommandHandler implements CommandHandler<UpdateMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;

  public UpdateMockFormCommandHandler(MockFormManager mockFormManager) {
    this.mockFormManager = mockFormManager;
  }

  @Transactional
  public HttpResult execute(UpdateMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MockFormPayload payload = command.getPayload();
    String mockId = Optional.ofNullable(command.getMockId()).orElse(payload.getMockId());

    MockFormEntity entity = mockFormManager.selectById(mockId);
    if (entity == null) {
      return HttpResult.error("要更新的记录不存在。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setExtend(payload.getExtend());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    mockFormManager.updateById(mockId, entity);

    return HttpResult.ok();
  }

}
