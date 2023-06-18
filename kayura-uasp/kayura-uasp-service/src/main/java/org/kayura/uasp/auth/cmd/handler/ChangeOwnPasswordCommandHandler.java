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
import org.kayura.event.EventGateway;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.account.ChangePwdPayload;
import org.kayura.uasp.auth.cmd.ChangeOwnPasswordCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.event.PasswordChangedEvent;
import org.kayura.uasp.auth.manage.UserManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChangeOwnPasswordCommandHandler implements CommandHandler<ChangeOwnPasswordCommand, HttpResult> {

  private final UserManager userManager;
  private final PasswordEncoder passwordEncoder;
  private final EventGateway eventGateway;

  public ChangeOwnPasswordCommandHandler(UserManager userManager,
                                         PasswordEncoder passwordEncoder,
                                         EventGateway eventGateway) {
    this.userManager = userManager;
    this.passwordEncoder = passwordEncoder;
    this.eventGateway = eventGateway;
  }

  @Transactional
  public HttpResult execute(ChangeOwnPasswordCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ChangePwdPayload payload = command.getPayload();

    String userId = loginUser.getUserId();
    UserEntity user = userManager.selectById(userId);
    if (user == null) {
      return HttpResult.error("当前用户数据已经不存在。");
    }

    if (!passwordEncoder.matches(payload.getOldPwd(), user.getPassword())) {
      return HttpResult.error("提供的原始密码错误。");
    }

    userManager.updatePassword(userId, payload.getNewPwd());

    eventGateway.publish(new PasswordChangedEvent(this, userId));

    return HttpResult.ok();
  }

}
