package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;

public class GetFormFlowCommand extends Command {

  private String flowId;

  public String getFlowId() {
    return flowId;
  }

  public GetFormFlowCommand setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }
}
