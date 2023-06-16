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
import org.kayura.security.vcode.ImageVerifyHandler;
import org.kayura.security.vcode.SmsVerifyHandler;
import org.kayura.security.vcode.VerifyCode;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.auth.cmd.SendSmsVerifyCommand;
import org.kayura.uasp.passport.SmsVerifyPayload;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SendSmsVerifyCommandHandler implements CommandHandler<SendSmsVerifyCommand, HttpResult> {

  private final ImageVerifyHandler imageVerifyHandler;
  private final SmsVerifyHandler smsVerifyHandler;

  public SendSmsVerifyCommandHandler(ImageVerifyHandler imageVerifyHandler,
                                     SmsVerifyHandler smsVerifyHandler) {
    this.imageVerifyHandler = imageVerifyHandler;
    this.smsVerifyHandler = smsVerifyHandler;
  }

  @Override
  public HttpResult execute(SendSmsVerifyCommand command) {

    HttpServletRequest request = command.getRequest();
    String useType = command.getTemplate();
    SmsVerifyPayload payload = command.getPayload();

    String sessionId = request.getSession(true).getId();
    if (!this.isMobile(payload.getMobile())) {
      return HttpResult.error("手机格式不正确。");
    }

    // 验证图形码
    Result verify = imageVerifyHandler.verify(sessionId, payload.getVcode());
    if (verify.isFail()) {
      return HttpResult.of(verify);
    }

    // 发送短信，并返回识别码
    VerifyCode smsCode = smsVerifyHandler.build(payload.getMobile(), useType);
    return HttpResult.okBody(smsCode);
  }

  protected boolean isMobile(String text) {

    assert text != null;

    if (text.length() == 11) {
      char[] chars = text.toCharArray();
      if (chars[0] == '1') {
        for (int i = 1; i < chars.length; i++) {
          if (!Character.isDigit(chars[i])) {
            return false;
          }
        }
        return true;
      }
    }

    return false;
  }
}
