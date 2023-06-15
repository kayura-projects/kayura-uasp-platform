package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.workflow.FormFlowPayload;

public class UpdateFormFlowCommand extends Command {

  private String flowId;
  private FormFlowPayload payload;

  public String getFlowId() {
    return flowId;
  }

  public UpdateFormFlowCommand setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  public FormFlowPayload getPayload() {
    return payload;
  }

  public UpdateFormFlowCommand setPayload(FormFlowPayload payload) {
    this.payload = payload;
    return this;
  }
}
