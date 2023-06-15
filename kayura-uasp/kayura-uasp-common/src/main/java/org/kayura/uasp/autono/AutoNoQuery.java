package org.kayura.uasp.autono;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;

import javax.validation.constraints.NotBlank;

public class AutoNoQuery {

  @NotBlank
  private String appId;
  @NotBlank
  @Ignore
  private String tenantId;
  @Like("code,name")
  private String searchText;

  public static AutoNoQuery create() {
    return new AutoNoQuery();
  }

  public String getAppId() {
    return appId;
  }

  public AutoNoQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AutoNoQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public AutoNoQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
