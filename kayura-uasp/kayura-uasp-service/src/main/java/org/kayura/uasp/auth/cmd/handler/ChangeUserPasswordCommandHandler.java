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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.event.EventGateway;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.ChangeUserPasswordCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.event.PasswordChangedEvent;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.config.AppSettings;
import org.kayura.uasp.user.ChangeModes;
import org.kayura.uasp.user.ChangeUserPasswordPayload;
import org.kayura.utils.DateUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ChangeUserPasswordCommandHandler implements CommandHandler<ChangeUserPasswordCommand, HttpResult> {

  private final UserManager userManager;
  private final PasswordEncoder passwordEncoder;
  private final AppSettings appSettings;
  private final EventGateway eventGateway;

  public ChangeUserPasswordCommandHandler(UserManager userManager,
                                          PasswordEncoder passwordEncoder,
                                          AppSettings appSettings,
                                          EventGateway eventGateway) {
    this.userManager = userManager;
    this.passwordEncoder = passwordEncoder;
    this.appSettings = appSettings;
    this.eventGateway = eventGateway;
  }

  @Transactional
  public HttpResult execute(ChangeUserPasswordCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String userId = loginUser.getUserId();
    ChangeUserPasswordPayload payload = command.getPayload();

    if (ChangeModes.MANUAL.equals(payload.getChangeMode())) {
      // 手工修改密码
      String encode = this.passwordEncoder.encode(payload.getPassword());
      LocalDateTime nextExpire = DateUtils.now().plusSeconds(this.appSettings.getPwdExpire());
      this.userManager.updateByWhere(w -> {
        w.set(UserEntity::getPassword, encode);
        w.set(UserEntity::getPasswordExpire, nextExpire);
        w.eq(UserEntity::getUserId, userId);
      });
    }

    eventGateway.publish(new PasswordChangedEvent(this, userId));

    return HttpResult.ok();
  }

}
