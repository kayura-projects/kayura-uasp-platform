package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;

public class GetDictItemCommand extends Command {

  private String itemId;

  public String getItemId() {
    return itemId;
  }

  public GetDictItemCommand setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }
}
