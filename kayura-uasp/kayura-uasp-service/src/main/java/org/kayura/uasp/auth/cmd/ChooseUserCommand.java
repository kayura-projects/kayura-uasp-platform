package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.UserTypes;

import java.util.List;

public class ChooseUserCommand extends Command {

  private String roleId;
  private String tenantId;
  private List<UserTypes> userTypes;

  public String getRoleId() {
    return roleId;
  }

  public ChooseUserCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseUserCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public List<UserTypes> getUserTypes() {
    return userTypes;
  }

  public ChooseUserCommand setUserTypes(List<UserTypes> userTypes) {
    this.userTypes = userTypes;
    return this;
  }
}
