package org.kayura.uasp.role;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class RoleQuery {

  @Ignore
  private String appId;
  @Ignore
  private String tenantId;
  private DataStatus status;
  @Like("code,name")
  private String searchText;

  public DataStatus getStatus() {
    return status;
  }

  public RoleQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public RoleQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public RoleQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public RoleQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
