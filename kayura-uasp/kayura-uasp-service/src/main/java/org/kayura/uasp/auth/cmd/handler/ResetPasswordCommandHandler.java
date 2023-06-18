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
import org.kayura.security.vcode.SmsVerifyHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.auth.cmd.ResetPasswordCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.event.PasswordChangedEvent;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.passport.ResetPasswordPayload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ResetPasswordCommandHandler implements CommandHandler<ResetPasswordCommand, HttpResult> {

  private final SmsVerifyHandler smsVerifyHandler;
  private final UserManager userManager;
  private final EventGateway eventGateway;

  public ResetPasswordCommandHandler(SmsVerifyHandler smsVerifyHandler,
                                     UserManager userManager,
                                     EventGateway eventGateway) {
    this.smsVerifyHandler = smsVerifyHandler;
    this.userManager = userManager;
    this.eventGateway = eventGateway;
  }

  @Transactional
  public HttpResult execute(ResetPasswordCommand command) {

    ResetPasswordPayload payload = command.getPayload();

    // 检查短信验证码，是否正确
    Result result = smsVerifyHandler.verify(payload.getMobile(), payload.getUcode(), payload.getVcode());
    if (result.isFail()) {
      return HttpResult.of(result);
    }

    // 更新密码
    UserEntity user = userManager.selectOne(w -> w.eq(UserEntity::getMobile, payload.getMobile()));
    if (user == null) {
      return HttpResult.error("用户不存在。");
    }

    userManager.updatePassword(user.getUserId(), payload.getPassword());

    eventGateway.publish(new PasswordChangedEvent(this, user));

    return HttpResult.ok();
  }

}
