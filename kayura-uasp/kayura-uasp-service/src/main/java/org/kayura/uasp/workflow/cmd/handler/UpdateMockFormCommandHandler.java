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
