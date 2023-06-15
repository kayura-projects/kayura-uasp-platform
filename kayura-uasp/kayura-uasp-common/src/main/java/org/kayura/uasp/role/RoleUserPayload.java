package org.kayura.uasp.role;

import javax.validation.constraints.NotBlank;

public class RoleUserPayload {

  @NotBlank
  private String userId;
  @NotBlank
  private String roleId;

  public static RoleUserPayload create() {
    return new RoleUserPayload();
  }

  public String getUserId() {
    return userId;
  }

  public RoleUserPayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getRoleId() {
    return roleId;
  }

  public RoleUserPayload setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }
}
