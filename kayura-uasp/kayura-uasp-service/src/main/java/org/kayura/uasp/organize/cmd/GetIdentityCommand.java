package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;

public class GetIdentityCommand extends Command {

  private String identityId;

  public String getIdentityId() {
    return identityId;
  }

  public GetIdentityCommand setIdentityId(String identityId) {
    this.identityId = identityId;
    return this;
  }
}
