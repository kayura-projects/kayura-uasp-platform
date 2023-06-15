package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.OrganizeTypes;

public class DeleteOrganizeCommand extends Command {

  private OrganizeTypes type;
  private String orgId;
  private IdPayload payload;

  public OrganizeTypes getType() {
    return type;
  }

  public DeleteOrganizeCommand setType(OrganizeTypes type) {
    this.type = type;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteOrganizeCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getOrgId() {
    return orgId;
  }

  public DeleteOrganizeCommand setOrgId(String orgId) {
    this.orgId = orgId;
    return this;
  }
}
