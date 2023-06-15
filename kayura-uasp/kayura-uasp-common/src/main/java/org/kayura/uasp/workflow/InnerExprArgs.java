package org.kayura.uasp.workflow;

import java.util.HashMap;
import java.util.Map;

public class InnerExprArgs {

  private String formCode;
  private String tenantId;
  private Map<String, Object> variables;

  public static InnerExprArgs create() {
    return new InnerExprArgs();
  }

  public String getFormCode() {
    return formCode;
  }

  public InnerExprArgs setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public InnerExprArgs setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public Map<String, Object> getVariables() {
    return variables;
  }

  public InnerExprArgs setVariables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public InnerExprArgs putVariable(String key, Object value) {
    if (this.variables == null) {
      this.variables = new HashMap<>();
    }
    this.variables.put(key, value);
    return this;
  }
}
