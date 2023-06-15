package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.tenant.TenantPayload;

public class CreateTenantCommand extends Command {

  private TenantPayload payload;

  public TenantPayload getPayload() {
    return payload;
  }

  public CreateTenantCommand setPayload(TenantPayload payload) {
    this.payload = payload;
    return this;
  }
}
