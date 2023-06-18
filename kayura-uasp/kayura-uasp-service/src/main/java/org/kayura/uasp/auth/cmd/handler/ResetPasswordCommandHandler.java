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
