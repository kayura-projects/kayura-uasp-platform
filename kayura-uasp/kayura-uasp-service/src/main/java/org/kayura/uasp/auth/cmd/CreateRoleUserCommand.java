package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RoleUserPayload;

import java.util.List;

public class CreateRoleUserCommand extends Command {

  private RoleUserPayload payload;
  private List<RoleUserPayload> payloads;

  public RoleUserPayload getPayload() {
    return payload;
  }

  public CreateRoleUserCommand setPayload(RoleUserPayload payload) {
    this.payload = payload;
    return this;
  }

  public List<RoleUserPayload> getPayloads() {
    return payloads;
  }

  public CreateRoleUserCommand setPayloads(List<RoleUserPayload> payloads) {
    this.payloads = payloads;
    return this;
  }
}
