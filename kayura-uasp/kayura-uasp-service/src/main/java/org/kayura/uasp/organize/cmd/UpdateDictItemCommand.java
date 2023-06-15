package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.dict.DictItemPayload;

public class UpdateDictItemCommand extends Command {

  private String itemId;
  private DictItemPayload payload;

  public String getItemId() {
    return itemId;
  }

  public UpdateDictItemCommand setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public DictItemPayload getPayload() {
    return payload;
  }

  public UpdateDictItemCommand setPayload(DictItemPayload payload) {
    this.payload = payload;
    return this;
  }
}
