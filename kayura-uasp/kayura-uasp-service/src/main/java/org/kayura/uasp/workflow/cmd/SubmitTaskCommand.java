package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.workflow.SubmitPayload;

public class SubmitTaskCommand extends Command {

  private SubmitPayload payload;

  public SubmitPayload getPayload() {
    return payload;
  }

  public SubmitTaskCommand setPayload(SubmitPayload payload) {
    this.payload = payload;
    return this;
  }
}
