package org.kayura.uasp.user;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;

public class ClientUserQuery {

  private Boolean enabled;
  private Boolean locked;
  @Like("userName,displayName,mobile")
  private String searchText;
  @Ignore
  private Boolean expired;

  public String getSearchText() {
    return searchText;
  }

  public ClientUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public ClientUserQuery setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public ClientUserQuery setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public Boolean getExpired() {
    return expired;
  }

  public ClientUserQuery setExpired(Boolean expired) {
    this.expired = expired;
    return this;
  }
}
