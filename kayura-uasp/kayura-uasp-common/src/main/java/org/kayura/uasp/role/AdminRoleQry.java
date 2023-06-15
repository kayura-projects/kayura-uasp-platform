package org.kayura.uasp.role;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class AdminRoleQry {

  private DataStatus status;
  @Like("code,name")
  private String searchText;

  public DataStatus getStatus() {
    return status;
  }

  public AdminRoleQry setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public AdminRoleQry setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
