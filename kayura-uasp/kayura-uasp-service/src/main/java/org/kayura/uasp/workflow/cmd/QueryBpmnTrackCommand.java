package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;

public class QueryBpmnTrackCommand extends Command {

  private String businessKey;

  public String getBusinessKey() {
    return businessKey;
  }

  public QueryBpmnTrackCommand setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }
}
