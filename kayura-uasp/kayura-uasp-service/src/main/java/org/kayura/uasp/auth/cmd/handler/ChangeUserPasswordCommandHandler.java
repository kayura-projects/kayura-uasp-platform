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
