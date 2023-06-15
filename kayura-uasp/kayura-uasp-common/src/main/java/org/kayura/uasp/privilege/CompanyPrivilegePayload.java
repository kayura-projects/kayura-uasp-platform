package org.kayura.uasp.privilege;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CompanyPrivilegePayload {

  @NotBlank
  private String companyId;
  @NotBlank
  private String appId;
  private List<ModuleAction> privileges;

  public String getCompanyId() {
    return companyId;
  }

  public CompanyPrivilegePayload setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public CompanyPrivilegePayload setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public CompanyPrivilegePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
