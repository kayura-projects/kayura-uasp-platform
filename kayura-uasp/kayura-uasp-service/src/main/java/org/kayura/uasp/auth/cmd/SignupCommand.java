package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.signup.SignupPayload;

public class SignupCommand extends Command {

  private SignupPayload payload;

  public SignupPayload getPayload() {
    return payload;
  }

  public SignupCommand setPayload(SignupPayload payload) {
    this.payload = payload;
    return this;
  }
}
