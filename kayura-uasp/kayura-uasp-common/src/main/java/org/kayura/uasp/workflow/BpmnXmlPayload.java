package org.kayura.uasp.workflow;

public class BpmnXmlPayload {

  private String id;
  private String body;

  public static BpmnXmlPayload create() {
    return new BpmnXmlPayload();
  }

  public String getId() {
    return id;
  }

  public BpmnXmlPayload setId(String id) {
    this.id = id;
    return this;
  }

  public String getBody() {
    return body;
  }

  public BpmnXmlPayload setBody(String body) {
    this.body = body;
    return this;
  }
}
