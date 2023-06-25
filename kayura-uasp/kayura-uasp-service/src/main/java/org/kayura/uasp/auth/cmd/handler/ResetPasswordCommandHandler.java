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
