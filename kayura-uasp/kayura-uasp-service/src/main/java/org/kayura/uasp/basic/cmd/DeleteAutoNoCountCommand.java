package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteAutoNoCountCommand extends Command {

  private String countId;
  private IdPayload payload;

  public String getCountId() {
    return countId;
  }

  public DeleteAutoNoCountCommand setCountId(String countId) {
    this.countId = countId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteAutoNoCountCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
