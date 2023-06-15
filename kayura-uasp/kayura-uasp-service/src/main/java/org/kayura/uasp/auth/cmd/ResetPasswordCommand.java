package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.passport.ResetPasswordPayload;

public class ResetPasswordCommand extends Command {

  private ResetPasswordPayload payload;

  public ResetPasswordPayload getPayload() {
    return payload;
  }

  public ResetPasswordCommand setPayload(ResetPasswordPayload payload) {
    this.payload = payload;
    return this;
  }
}
