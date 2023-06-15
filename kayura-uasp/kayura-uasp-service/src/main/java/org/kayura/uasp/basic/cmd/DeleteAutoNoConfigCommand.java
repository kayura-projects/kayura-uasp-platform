package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteAutoNoConfigCommand extends Command {

  private IdPayload payload;
  private String configId;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteAutoNoConfigCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getConfigId() {
    return configId;
  }

  public DeleteAutoNoConfigCommand setConfigId(String configId) {
    this.configId = configId;
    return this;
  }
}
