package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.workflow.FormFlowPayload;

public class CreateFormFlowCommand extends Command {

  private FormFlowPayload payload;

  public FormFlowPayload getPayload() {
    return payload;
  }

  public CreateFormFlowCommand setPayload(FormFlowPayload payload) {
    this.payload = payload;
    return this;
  }
}
