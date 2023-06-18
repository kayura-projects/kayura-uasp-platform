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
