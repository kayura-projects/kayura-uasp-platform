package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.privilege.PrivilegeTypes;

public class QueryPrivilegeCommand extends Command {

  private PrivilegeTypes type;
  private String appId;
  private String linkId;
  private boolean authScope;

  public PrivilegeTypes getType() {
    return type;
  }

  public QueryPrivilegeCommand setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public QueryPrivilegeCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public boolean isAuthScope() {
    return authScope;
  }

  public QueryPrivilegeCommand setAuthScope(boolean authScope) {
    this.authScope = authScope;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public QueryPrivilegeCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
