package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.account.ChangePwdPayload;

public class ChangeOwnPasswordCommand extends Command {

  private ChangePwdPayload payload;

  public ChangePwdPayload getPayload() {
    return payload;
  }

  public ChangeOwnPasswordCommand setPayload(ChangePwdPayload payload) {
    this.payload = payload;
    return this;
  }
}
