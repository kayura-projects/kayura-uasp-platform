package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RolePayload;
import org.kayura.uasp.role.RoleTypes;

public class CreateRoleCommand extends Command {

  private RoleTypes roleType;
  private RolePayload payload;

  public RoleTypes getRoleType() {
    return roleType;
  }

  public CreateRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }

  public RolePayload getPayload() {
    return payload;
  }

  public CreateRoleCommand setPayload(RolePayload payload) {
    this.payload = payload;
    return this;
  }
}
