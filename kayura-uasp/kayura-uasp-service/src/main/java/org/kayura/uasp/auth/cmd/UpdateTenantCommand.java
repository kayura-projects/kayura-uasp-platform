package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.tenant.TenantPayload;

public class UpdateTenantCommand extends Command {

  private String tenantId;
  private TenantPayload payload;

  public String getTenantId() {
    return tenantId;
  }

  public UpdateTenantCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public TenantPayload getPayload() {
    return payload;
  }

  public UpdateTenantCommand setPayload(TenantPayload payload) {
    this.payload = payload;
    return this;
  }
}
