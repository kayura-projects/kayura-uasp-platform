package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.dict.DictItemPayload;

public class CreateDictItemCommand extends Command {

  private DictItemPayload payload;

  public DictItemPayload getPayload() {
    return payload;
  }

  public CreateDictItemCommand setPayload(DictItemPayload payload) {
    this.payload = payload;
    return this;
  }
}
