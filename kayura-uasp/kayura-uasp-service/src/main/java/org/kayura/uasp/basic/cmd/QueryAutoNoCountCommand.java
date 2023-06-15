package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;

public class QueryAutoNoCountCommand extends Command {

  private String configId;
  private String tenantId;

  public String getConfigId() {
    return configId;
  }

  public QueryAutoNoCountCommand setConfigId(String configId) {
    this.configId = configId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public QueryAutoNoCountCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
