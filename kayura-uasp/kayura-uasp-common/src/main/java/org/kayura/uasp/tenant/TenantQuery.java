package org.kayura.uasp.tenant;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.UsableStatus;

public class TenantQuery {

  private UsableStatus status;
  @Like("code,name")
  private String searchText;

  public UsableStatus getStatus() {
    return status;
  }

  public TenantQuery setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public TenantQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
