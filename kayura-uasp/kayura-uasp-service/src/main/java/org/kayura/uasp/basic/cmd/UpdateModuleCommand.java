package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.func.ModulePayload;

public class UpdateModuleCommand extends Command {

  private String moduleId;
  private ModulePayload payload;

  public String getModuleId() {
    return moduleId;
  }

  public UpdateModuleCommand setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public ModulePayload getPayload() {
    return payload;
  }

  public UpdateModuleCommand setPayload(ModulePayload payload) {
    this.payload = payload;
    return this;
  }
}
