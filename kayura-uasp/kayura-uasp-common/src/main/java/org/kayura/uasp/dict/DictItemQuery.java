package org.kayura.uasp.dict;

import org.kayura.mybatis.annotation.querier.Like;

import javax.validation.constraints.NotBlank;

public class DictItemQuery {

  @NotBlank
  private String defineId;
  @Like("code,name")
  private String searchText;

  public String getDefineId() {
    return defineId;
  }

  public DictItemQuery setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public DictItemQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

}
