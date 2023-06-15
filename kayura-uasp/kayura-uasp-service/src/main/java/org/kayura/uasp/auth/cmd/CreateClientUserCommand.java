package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.user.ClientUserPayload;

public class CreateClientUserCommand extends Command {

  private ClientUserPayload payload;

  public ClientUserPayload getPayload() {
    return payload;
  }

  public CreateClientUserCommand setPayload(ClientUserPayload payload) {
    this.payload = payload;
    return this;
  }
}
