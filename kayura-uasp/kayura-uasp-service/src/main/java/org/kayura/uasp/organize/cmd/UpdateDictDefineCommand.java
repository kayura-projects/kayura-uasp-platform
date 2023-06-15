package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.dict.DictDefinePayload;

public class UpdateDictDefineCommand extends Command {

  private String defineId;
  private DictDefinePayload payload;

  public String getDefineId() {
    return defineId;
  }

  public UpdateDictDefineCommand setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public DictDefinePayload getPayload() {
    return payload;
  }

  public UpdateDictDefineCommand setPayload(DictDefinePayload payload) {
    this.payload = payload;
    return this;
  }
}
