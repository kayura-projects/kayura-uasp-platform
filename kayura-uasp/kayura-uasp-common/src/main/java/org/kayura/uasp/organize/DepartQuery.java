package org.kayura.uasp.organize;

import org.kayura.mybatis.annotation.querier.Like;

public class DepartQuery {

  private String companyId;
  @Like("code,name")
  private String searchText;

  public String getCompanyId() {
    return companyId;
  }

  public DepartQuery setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public DepartQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
