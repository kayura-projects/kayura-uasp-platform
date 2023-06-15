package org.kayura.uasp.privilege;

import java.util.List;

public class PrivilegeBody {

  private String appId;
  private String linkId;
  private PrivilegeTypes type;
  private List<ModuleAction> privileges;

  public static PrivilegeBody create() {
    return new PrivilegeBody();
  }

  public String getLinkId() {
    return linkId;
  }

  public PrivilegeBody setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public PrivilegeTypes getType() {
    return type;
  }

  public PrivilegeBody setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public PrivilegeBody setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public PrivilegeBody setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
