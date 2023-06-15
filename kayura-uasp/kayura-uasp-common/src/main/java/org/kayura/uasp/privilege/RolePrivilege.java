package org.kayura.uasp.privilege;

import java.util.List;

public class RolePrivilege {

  private String roleId;
  private String roleCode;
  private String roleName;
  private List<ModuleAction> auth;

  public static RolePrivilege create() {
    return new RolePrivilege();
  }

  public String getRoleId() {
    return roleId;
  }

  public RolePrivilege setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public RolePrivilege setRoleCode(String roleCode) {
    this.roleCode = roleCode;
    return this;
  }

  public String getRoleName() {
    return roleName;
  }

  public RolePrivilege setRoleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  public List<ModuleAction> getAuth() {
    return auth;
  }

  public RolePrivilege setAuth(List<ModuleAction> auth) {
    this.auth = auth;
    return this;
  }
}
