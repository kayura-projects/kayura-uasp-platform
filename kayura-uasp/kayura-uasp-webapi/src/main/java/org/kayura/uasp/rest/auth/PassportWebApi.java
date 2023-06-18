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
package org.kayura.uasp.rest.auth;

import org.kayura.cmd.CommandGateway;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.passport.LoginPayload;
import org.kayura.uasp.passport.ResetPasswordPayload;
import org.kayura.uasp.passport.SmsVerifyPayload;
import org.kayura.uasp.signup.SignupPayload;
import org.kayura.uasp.utils.UaspConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class PassportWebApi implements UaspConstants {

  private final CommandGateway commandGateway;

  public PassportWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/passport/sign-in")
  public HttpResult loginAndGetToken(CreateTokenCommand command,
                                     @RequestBody @Validated LoginPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/passport/refresh")
  public HttpResult refreshAndGetToken(RefreshTokenCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/passport/logout")
  public HttpResult logoutToken(LogoutTokenCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/passport/reset-pwd")
  public HttpResult resetPassword(ResetPasswordCommand command,
                                  @RequestBody @Validated ResetPasswordPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/passport/sign-up")
  public HttpResult signupUser(SignupCommand command,
                               @RequestBody @Validated SignupPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/passport/img-code")
  public HttpResult sendImageCode(SendImageVerifyCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/passport/sms-code")
  public HttpResult sendSmsCode(SendSmsVerifyCommand command,
                                @RequestBody @Validated SmsVerifyPayload payload) {

    return commandGateway.send(command
      .setPayload(payload)
      .setTemplate(SMS_REGISTER)
    );
  }
}
