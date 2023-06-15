package org.kayura.uasp.privilege;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RolePrivilegePayload {

  @NotBlank
  private String roleId;
  private List<ModuleAction> privileges;

  public String getRoleId() {
    return roleId;
  }

  public RolePrivilegePayload setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public RolePrivilegePayload setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }
}
