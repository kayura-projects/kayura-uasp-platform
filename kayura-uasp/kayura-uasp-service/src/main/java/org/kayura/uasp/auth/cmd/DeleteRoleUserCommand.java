package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RoleUserPayload;

import java.util.List;

public class DeleteRoleUserCommand extends Command {

  private String roleId;
  private String userId;
  private RoleUserPayload payload;
  private List<RoleUserPayload> payloads;

  public RoleUserPayload getPayload() {
    return payload;
  }

  public DeleteRoleUserCommand setPayload(RoleUserPayload payload) {
    this.payload = payload;
    return this;
  }

  public List<RoleUserPayload> getPayloads() {
    return payloads;
  }

  public DeleteRoleUserCommand setPayloads(List<RoleUserPayload> payloads) {
    this.payloads = payloads;
    return this;
  }

  public String getRoleId() {
    return roleId;
  }

  public DeleteRoleUserCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public DeleteRoleUserCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
