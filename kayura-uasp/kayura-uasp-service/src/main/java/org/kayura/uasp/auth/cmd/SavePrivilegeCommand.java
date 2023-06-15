package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.privilege.ModuleAction;
import org.kayura.uasp.privilege.PrivilegeTypes;

import java.util.List;

public class SavePrivilegeCommand extends Command {

  private String appId;
  private PrivilegeTypes type;
  private String linkId;
  private List<ModuleAction> privileges;

  public PrivilegeTypes getType() {
    return type;
  }

  public SavePrivilegeCommand setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public SavePrivilegeCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public SavePrivilegeCommand setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public SavePrivilegeCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
