package org.kayura.uasp.workflow;

import javax.validation.constraints.NotBlank;

public class BpmnTrackQuery {

  @NotBlank
  private String businessKey;

  public static BpmnTrackQuery create() {
    return new BpmnTrackQuery();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public BpmnTrackQuery setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }
}
