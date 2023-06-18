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
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.SendImageVerifyCommand;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SendImageVerifyCommandHandler implements CommandHandler<SendImageVerifyCommand, HttpResult> {

  private final ImageVerifyHandler imageVerifyHandler;

  public SendImageVerifyCommandHandler(ImageVerifyHandler imageVerifyHandler) {
    this.imageVerifyHandler = imageVerifyHandler;
  }

  @Override
  public HttpResult execute(SendImageVerifyCommand command) {

    HttpServletRequest request = command.getRequest();
    String sessionId = request.getSession(true).getId();
    String imgCode = imageVerifyHandler.build(sessionId);
    return HttpResult.okBody(imgCode);
  }

}
