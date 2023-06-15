package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.IdentityPayload;

public class CreateIdentityCommand extends Command {

  private IdentityPayload payload;

  public IdentityPayload getPayload() {
    return payload;
  }

  public CreateIdentityCommand setPayload(IdentityPayload payload) {
    this.payload = payload;
    return this;
  }
}
