package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.workflow.StartPayload;

public class StartProcessCommand extends Command {

  private StartPayload payload;

  public StartPayload getPayload() {
    return payload;
  }

  public StartProcessCommand setPayload(StartPayload payload) {
    this.payload = payload;
    return this;
  }
}
