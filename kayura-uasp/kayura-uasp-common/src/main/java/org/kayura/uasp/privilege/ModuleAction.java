package org.kayura.uasp.privilege;

import org.kayura.type.StringList;

public class ModuleAction {

  private String moduleId;
  private StringList actions;

  public static ModuleAction create() {
    return new ModuleAction();
  }

  public String getModuleId() {
    return moduleId;
  }

  public ModuleAction setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public StringList getActions() {
    return actions;
  }

  public ModuleAction setActions(StringList actions) {
    this.actions = actions;
    return this;
  }
}
