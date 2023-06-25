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
package org.kayura.uasp.rest.auth;

import org.kayura.cmd.CommandGateway;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.passport.LoginPayload;
import org.kayura.uasp.passport.ResetPasswordPayload;
import org.kayura.uasp.passport.SmsVerifyPayload;
import org.kayura.uasp.signup.SignupPayload;
import org.kayura.uasp.utils.UaspConsts;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class PassportWebApi implements UaspConsts {

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
