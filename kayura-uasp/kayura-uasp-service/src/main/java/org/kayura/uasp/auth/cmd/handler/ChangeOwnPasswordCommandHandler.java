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
