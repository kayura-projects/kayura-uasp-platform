package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteTenantCommand extends Command {

  private IdPayload payload;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteTenantCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
