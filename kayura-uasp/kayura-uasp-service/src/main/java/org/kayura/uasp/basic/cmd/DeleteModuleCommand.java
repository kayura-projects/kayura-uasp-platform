package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteModuleCommand extends Command {

  private String moduleId;
  private IdPayload payload;

  public String getModuleId() {
    return moduleId;
  }

  public DeleteModuleCommand setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteModuleCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
