package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;

import java.util.Set;

public class DeleteIdentityCommand extends Command {

  private String identityId;
  private Set<String> identityIds;

  public String getIdentityId() {
    return identityId;
  }

  public DeleteIdentityCommand setIdentityId(String identityId) {
    this.identityId = identityId;
    return this;
  }

  public Set<String> getIdentityIds() {
    return identityIds;
  }

  public DeleteIdentityCommand setIdentityIds(Set<String> identityIds) {
    this.identityIds = identityIds;
    return this;
  }
}
