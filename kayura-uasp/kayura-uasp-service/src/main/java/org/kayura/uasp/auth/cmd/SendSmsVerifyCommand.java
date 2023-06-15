package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.passport.SmsVerifyPayload;

public class SendSmsVerifyCommand extends Command {

  private String template;
  private SmsVerifyPayload payload;

  public SmsVerifyPayload getPayload() {
    return payload;
  }

  public SendSmsVerifyCommand setPayload(SmsVerifyPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getTemplate() {
    return template;
  }

  public SendSmsVerifyCommand setTemplate(String template) {
    this.template = template;
    return this;
  }
}
