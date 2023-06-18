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
