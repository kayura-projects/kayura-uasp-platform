package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RolePayload;
import org.kayura.uasp.role.RoleTypes;

public class UpdateRoleCommand extends Command {

  private String roleId;
  private RolePayload payload;
  private RoleTypes roleType;

  public String getRoleId() {
    return roleId;
  }

  public UpdateRoleCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public RolePayload getPayload() {
    return payload;
  }

  public UpdateRoleCommand setPayload(RolePayload payload) {
    this.payload = payload;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public UpdateRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }
}
