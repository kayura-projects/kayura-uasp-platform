package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.passport.LoginPayload;

public class CreateTokenCommand extends Command {

  private LoginPayload payload;

  public LoginPayload getPayload() {
    return payload;
  }

  public CreateTokenCommand setPayload(LoginPayload payload) {
    this.payload = payload;
    return this;
  }
}
