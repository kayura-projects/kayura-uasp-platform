package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.role.RoleTypes;

public class DeleteRoleCommand extends Command {

  private RoleTypes roleType;
  private IdPayload payload;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteRoleCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public DeleteRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }
}
