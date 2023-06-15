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
