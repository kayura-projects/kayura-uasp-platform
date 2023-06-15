package org.kayura.uasp.form;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

import javax.validation.constraints.NotBlank;

public class FormDefineQuery {

  @NotBlank
  private String appId;
  private DataStatus status;
  @Like("code,name")
  private String searchText;
  @Ignore
  private Boolean needField;

  public String getAppId() {
    return appId;
  }

  public FormDefineQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormDefineQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public FormDefineQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getNeedField() {
    return needField;
  }

  public FormDefineQuery setNeedField(Boolean needField) {
    this.needField = needField;
    return this;
  }
}
