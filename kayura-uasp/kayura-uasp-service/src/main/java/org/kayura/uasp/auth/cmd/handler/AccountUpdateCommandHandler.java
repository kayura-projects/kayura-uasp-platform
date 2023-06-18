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
import org.kayura.security.LoginUser;
import org.kayura.security.vcode.SmsVerifyHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.account.AccountPayload;
import org.kayura.uasp.auth.cmd.AccountUpdateCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountUpdateCommandHandler implements CommandHandler<AccountUpdateCommand, HttpResult> {

  private final UserManager userManager;
  private final SmsVerifyHandler smsVerifyHandler;

  public AccountUpdateCommandHandler(UserManager userManager,
                                     SmsVerifyHandler smsVerifyHandler) {
    this.userManager = userManager;
    this.smsVerifyHandler = smsVerifyHandler;
  }

  @Transactional
  public HttpResult execute(AccountUpdateCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AccountPayload payload = command.getPayload();

    // 如果修改了手机号，需要检查短信验证码
    if (userManager.mobileIsChange(loginUser.getUserId(), payload.getMobile())) {
      Result verify = smsVerifyHandler.verify(payload.getMobile(), payload.getUcode(), payload.getVcode());
      if (verify.isFail()) {
        return HttpResult.of(verify);
      }
    }

    UserEntity entity = userManager.selectById(loginUser.getUserId());
    entity.setAvatar(payload.getAvatar());
    entity.setDisplayName(payload.getDisplayName());
    entity.setMobile(payload.getMobile());
    entity.setEmail(payload.getEmail());
    userManager.updateById(loginUser.getUserId(), entity);

    return HttpResult.ok();
  }

}
