package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.dict.DictDefinePayload;

public class CreateDictDefineCommand extends Command {

  private DictDefinePayload payload;

  public DictDefinePayload getPayload() {
    return payload;
  }

  public CreateDictDefineCommand setPayload(DictDefinePayload payload) {
    this.payload = payload;
    return this;
  }
}
