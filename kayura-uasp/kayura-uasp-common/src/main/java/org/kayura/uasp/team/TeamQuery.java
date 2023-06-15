package org.kayura.uasp.team;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class TeamQuery {

  private String tenantId;
  private DataStatus status;
  @Like("code,name,remark")
  private String searchText;

  public DataStatus getStatus() {
    return status;
  }

  public TeamQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public TeamQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public TeamQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
