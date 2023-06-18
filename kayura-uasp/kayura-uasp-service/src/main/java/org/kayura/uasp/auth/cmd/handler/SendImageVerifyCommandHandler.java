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
