package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.autono.AutoNoPayload;

public class UpdateAutoNoConfigCommand extends Command {

  private AutoNoPayload payload;

  public AutoNoPayload getPayload() {
    return payload;
  }

  public UpdateAutoNoConfigCommand setPayload(AutoNoPayload payload) {
    this.payload = payload;
    return this;
  }
}
