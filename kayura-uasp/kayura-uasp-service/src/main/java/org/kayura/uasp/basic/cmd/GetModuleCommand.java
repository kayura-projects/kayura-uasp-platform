package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;

public class GetModuleCommand extends Command {

  private String moduleId;

  public String getModuleId() {
    return moduleId;
  }

  public GetModuleCommand setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }
}
