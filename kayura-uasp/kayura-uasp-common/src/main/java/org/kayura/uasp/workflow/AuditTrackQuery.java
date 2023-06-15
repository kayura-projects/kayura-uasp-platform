package org.kayura.uasp.workflow;

import java.util.HashMap;

public class AuditTrackQuery {

  private String businessKey;
  private String userId;
  private String tenantId;
  private String formCode;
  private HashMap<String, Object> variables;

  public static AuditTrackQuery create() {
    return new AuditTrackQuery();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public AuditTrackQuery setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public AuditTrackQuery setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AuditTrackQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getFormCode() {
    return formCode;
  }

  public AuditTrackQuery setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public HashMap<String, Object> getVariables() {
    return variables;
  }

  public AuditTrackQuery setVariables(HashMap<String, Object> variables) {
    this.variables = variables;
    return this;
  }
}
