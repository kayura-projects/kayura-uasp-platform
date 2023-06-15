package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.func.ModuleTypes;

import java.util.Set;

public class ChooseModuleCommand extends Command {

  private String appId;
  private Set<ModuleTypes> types;

  public String getAppId() {
    return appId;
  }

  public ChooseModuleCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Set<ModuleTypes> getTypes() {
    return types;
  }

  public ChooseModuleCommand setTypes(Set<ModuleTypes> types) {
    this.types = types;
    return this;
  }

  public ChooseModuleCommand setTypes(ModuleTypes... types) {
    this.types = Set.of(types);
    return this;
  }
}
