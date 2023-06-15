package org.kayura.uasp.workflow;

import java.util.HashMap;

public class ChooseFlowQry {

  private String tenantId;
  private String formCode;
  private HashMap<String, Object> variables;

  public static ChooseFlowQry create() {
    return new ChooseFlowQry();
  }

  public String getFormCode() {
    return formCode;
  }

  public ChooseFlowQry setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseFlowQry setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public HashMap<String, Object> getVariables() {
    return variables;
  }

  public ChooseFlowQry setVariables(HashMap<String, Object> variables) {
    this.variables = variables;
    return this;
  }
}
