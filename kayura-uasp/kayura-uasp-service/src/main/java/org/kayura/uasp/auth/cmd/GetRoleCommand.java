package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;

public class GetRoleCommand extends Command {

  private String roleId;

  public String getRoleId() {
    return roleId;
  }

  public GetRoleCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }
}
