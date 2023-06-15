package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteDictItemCommand extends Command {

  private String itemId;
  private IdPayload payload;

  public String getItemId() {
    return itemId;
  }

  public DeleteDictItemCommand setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteDictItemCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
