package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.user.ChangeUserPasswordPayload;

public class ChangeUserPasswordCommand extends Command {

  private ChangeUserPasswordPayload payload;

  public ChangeUserPasswordPayload getPayload() {
    return payload;
  }

  public ChangeUserPasswordCommand setPayload(ChangeUserPasswordPayload payload) {
    this.payload = payload;
    return this;
  }
}
