package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;

public class GetAdminUserCommand extends Command {

  private String userId;

  public String getUserId() {
    return userId;
  }

  public GetAdminUserCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
