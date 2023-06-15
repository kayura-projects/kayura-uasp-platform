package org.kayura.uasp.organize;

import org.kayura.mybatis.annotation.querier.Like;

public class PositionQry {

  private String departId;
  @Like("code,name")
  private String searchText;

  public String getDepartId() {
    return departId;
  }

  public PositionQry setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public PositionQry setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
