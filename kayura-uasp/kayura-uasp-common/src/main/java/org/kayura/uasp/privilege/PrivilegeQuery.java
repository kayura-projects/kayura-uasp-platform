package org.kayura.uasp.privilege;

import org.kayura.mybatis.annotation.querier.Like;

public class PrivilegeQuery {

  private PrivilegeTypes type;
  private String linkId;
  @Like("code,name")
  private String searchText;

  public static PrivilegeQuery create() {
    return new PrivilegeQuery();
  }

  public String getLinkId() {
    return linkId;
  }

  public PrivilegeQuery setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public PrivilegeQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public PrivilegeTypes getType() {
    return type;
  }

  public PrivilegeQuery setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }
}
