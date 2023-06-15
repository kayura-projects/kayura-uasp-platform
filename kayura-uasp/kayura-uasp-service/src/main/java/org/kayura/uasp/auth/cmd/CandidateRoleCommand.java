package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.utils.OutputTypes;

public class CandidateRoleCommand extends Command {

  private OutputTypes output;
  private String userId;
  private String appId;
  private String tenantId;
  private RoleTypes roleType;

  public OutputTypes getOutput() {
    return output;
  }

  public CandidateRoleCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public CandidateRoleCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public CandidateRoleCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CandidateRoleCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public CandidateRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }
}
