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
