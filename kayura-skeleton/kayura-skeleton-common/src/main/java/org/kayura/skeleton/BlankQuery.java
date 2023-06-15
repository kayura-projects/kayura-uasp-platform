package org.kayura.skeleton;

import org.kayura.mybatis.annotation.querier.Like;

public class BlankQuery {

  @Like("code,name")
  private String searchText;

  public String getSearchText() {
    return searchText;
  }

  public BlankQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
