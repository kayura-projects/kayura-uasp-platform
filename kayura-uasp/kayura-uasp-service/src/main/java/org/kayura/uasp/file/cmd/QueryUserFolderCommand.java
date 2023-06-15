package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;

public class QueryUserFolderCommand extends Command {

  private String userId;

  public String getUserId() {
    return userId;
  }

  public QueryUserFolderCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }

}

