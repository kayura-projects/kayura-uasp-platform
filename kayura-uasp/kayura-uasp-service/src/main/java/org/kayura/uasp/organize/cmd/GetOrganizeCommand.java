package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.OrganizeTypes;

public class GetOrganizeCommand extends Command {

  private OrganizeTypes orgType;
  private String orgId;

  public OrganizeTypes getOrgType() {
    return orgType;
  }

  public GetOrganizeCommand setOrgType(OrganizeTypes orgType) {
    this.orgType = orgType;
    return this;
  }

  public String getOrgId() {
    return orgId;
  }

  public GetOrganizeCommand setOrgId(String orgId) {
    this.orgId = orgId;
    return this;
  }
}
