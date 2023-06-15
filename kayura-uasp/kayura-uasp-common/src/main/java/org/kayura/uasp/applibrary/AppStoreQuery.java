package org.kayura.uasp.applibrary;

import org.kayura.mybatis.annotation.querier.Like;

import javax.validation.constraints.NotBlank;

public class AppStoreQuery {

  @NotBlank
  private String appId;
  @Like("title,description")
  private String searchText;

  public static AppStoreQuery create() {
    return new AppStoreQuery();
  }

  public String getAppId() {
    return appId;
  }

  public AppStoreQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public AppStoreQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
