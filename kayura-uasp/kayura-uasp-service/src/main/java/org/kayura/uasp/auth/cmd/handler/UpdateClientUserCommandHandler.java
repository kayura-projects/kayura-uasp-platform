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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.UpdateClientUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.ClientUserPayload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateClientUserCommandHandler implements CommandHandler<UpdateClientUserCommand, HttpResult> {

  private final UserManager userManager;

  public UpdateClientUserCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional
  public HttpResult execute(UpdateClientUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ClientUserPayload payload = command.getPayload();
    String clientId = Optional.ofNullable(command.getClientId()).orElse(payload.getClientId());

    UserEntity entity = userManager.selectById(clientId);
    if (entity == null) {
      return HttpResult.error("更新的账号不存在。");
    }

    entity.setPassword(payload.getSecretKey());
    entity.setAccountExpire(payload.getAccountExpire());
    entity.setEnabled(payload.getEnabled());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    userManager.updateById(clientId, entity);

    return HttpResult.ok();
  }

}
