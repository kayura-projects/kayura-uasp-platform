package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.autono.AutoNoPayload;

public class CreateAutoNoConfigCommand extends Command {

  private AutoNoPayload payload;

  public AutoNoPayload getPayload() {
    return payload;
  }

  public CreateAutoNoConfigCommand setPayload(AutoNoPayload payload) {
    this.payload = payload;
    return this;
  }
}
