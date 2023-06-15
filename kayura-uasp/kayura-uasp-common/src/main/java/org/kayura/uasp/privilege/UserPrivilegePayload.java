package org.kayura.uasp.privilege;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserPrivilegePayload {

  private String appId;
  @NotBlank
  private String userId;
  private List<ModuleAction> privileges;

  public String getUserId() {
    return userId;
  }

  public UserPrivilegePayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public UserPrivilegePayload setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public UserPrivilegePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
