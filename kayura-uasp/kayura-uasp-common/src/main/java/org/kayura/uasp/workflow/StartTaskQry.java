package org.kayura.uasp.workflow;

import java.util.HashMap;

public class StartTaskQry {

  private String businessKey;
  private String userId;
  private String tenantId;
  private String formCode;
  private HashMap<String, Object> variables;

  public static StartTaskQry create() {
    return new StartTaskQry();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public StartTaskQry setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public StartTaskQry setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public StartTaskQry setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getFormCode() {
    return formCode;
  }

  public StartTaskQry setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public HashMap<String, Object> getVariables() {
    return variables;
  }

  public StartTaskQry setVariables(HashMap<String, Object> variables) {
    this.variables = variables;
    return this;
  }
}
