package org.kayura.uasp.workflow;

public class UsedOpinionFrm {

  private String userId;
  private String content;

  public String getUserId() {
    return userId;
  }

  public UsedOpinionFrm setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public UsedOpinionFrm setContent(String content) {
    this.content = content;
    return this;
  }
}
