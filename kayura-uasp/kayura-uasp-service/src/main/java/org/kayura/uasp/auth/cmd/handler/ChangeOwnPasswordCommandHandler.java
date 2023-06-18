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
