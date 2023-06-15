package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;

public class GetAutoNoConfigCommand extends Command {

  private String configId;

  public String getConfigId() {
    return configId;
  }

  public GetAutoNoConfigCommand setConfigId(String configId) {
    this.configId = configId;
    return this;
  }
}
