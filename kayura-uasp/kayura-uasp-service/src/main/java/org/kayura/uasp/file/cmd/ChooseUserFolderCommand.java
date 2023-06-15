package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;

public class ChooseUserFolderCommand extends Command {

  private String userId;

  public String getUserId() {
    return userId;
  }

  public ChooseUserFolderCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
