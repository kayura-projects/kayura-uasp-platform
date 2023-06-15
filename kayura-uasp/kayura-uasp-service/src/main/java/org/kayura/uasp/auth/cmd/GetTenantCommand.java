package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;

public class GetTenantCommand extends Command {

  private String tenantId;

  public String getTenantId() {
    return tenantId;
  }

  public GetTenantCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
