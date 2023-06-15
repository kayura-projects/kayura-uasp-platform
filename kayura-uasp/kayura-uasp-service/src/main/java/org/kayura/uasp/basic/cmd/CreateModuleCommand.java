package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.func.ModulePayload;

public class CreateModuleCommand extends Command {

  private ModulePayload payload;

  public ModulePayload getPayload() {
    return payload;
  }

  public CreateModuleCommand setPayload(ModulePayload payload) {
    this.payload = payload;
    return this;
  }
}
