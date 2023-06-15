package org.kayura.uasp.autono;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public class NewNoArgs {

  @NotBlank
  private String code;
  private String tenantId;
  private Map<String, String> params;

  public static NewNoArgs create() {
    return new NewNoArgs();
  }

  public String getTenantId() {
    return tenantId;
  }

  public NewNoArgs setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public NewNoArgs setParams(Map<String, String> params) {
    this.params = params;
    return this;
  }

  public NewNoArgs putParam(String key, String value) {
    if (this.params == null) {
      this.params = new HashMap<>();
    }
    this.params.put(key, value);
    return this;
  }

  public String getCode() {
    return code;
  }

  public NewNoArgs setCode(String code) {
    this.code = code;
    return this;
  }
}
