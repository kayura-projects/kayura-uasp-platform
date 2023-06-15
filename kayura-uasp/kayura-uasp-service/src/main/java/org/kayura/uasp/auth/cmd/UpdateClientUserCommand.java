package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.user.ClientUserPayload;

public class UpdateClientUserCommand extends Command {

  private String clientId;
  private ClientUserPayload payload;

  public String getClientId() {
    return clientId;
  }

  public UpdateClientUserCommand setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public ClientUserPayload getPayload() {
    return payload;
  }

  public UpdateClientUserCommand setPayload(ClientUserPayload payload) {
    this.payload = payload;
    return this;
  }
}
