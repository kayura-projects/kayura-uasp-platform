/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
