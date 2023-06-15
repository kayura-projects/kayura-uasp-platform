package org.kayura.uasp.workflow;

import javax.validation.constraints.NotBlank;

public class AuditTaskQry {

  @NotBlank
  private String businessKey;
  private String userId;
  private String taskId;

  public static AuditTaskQry create() {
    return new AuditTaskQry();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public AuditTaskQry setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public AuditTaskQry setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getTaskId() {
    return taskId;
  }

  public AuditTaskQry setTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

}
