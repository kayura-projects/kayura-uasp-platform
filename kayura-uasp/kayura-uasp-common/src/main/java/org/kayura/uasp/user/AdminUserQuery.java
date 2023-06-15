package org.kayura.uasp.user;

import org.kayura.mybatis.annotation.querier.Like;

public class AdminUserQuery {

  private Boolean enabled;
  private Boolean locked;
  @Like("userName,displayName,mobile")
  private String searchText;

  public String getSearchText() {
    return searchText;
  }

  public AdminUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public AdminUserQuery setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public AdminUserQuery setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }
}
