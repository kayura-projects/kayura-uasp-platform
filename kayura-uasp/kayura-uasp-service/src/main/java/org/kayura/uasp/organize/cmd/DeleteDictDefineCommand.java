package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteDictDefineCommand extends Command {

  private String defineId;
  private IdPayload payload;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteDictDefineCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public DeleteDictDefineCommand setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }
}
