package org.kayura.uasp.role;

import org.kayura.mybatis.annotation.querier.Like;

public class RoleUserQuery {

  private String companyId;
  @Like("roleCode,roleName")
  private String searchText;

  public static RoleUserQuery create() {
    return new RoleUserQuery();
  }

  public String getSearchText() {
    return searchText;
  }

  public RoleUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public RoleUserQuery setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }
}
