package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;

public class GetClientUserCommand extends Command {

  private String userId;

  public String getUserId() {
    return userId;
  }

  public GetClientUserCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
