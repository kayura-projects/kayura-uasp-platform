package org.kayura.uasp.func;

import org.kayura.mybatis.annotation.querier.IsNull;
import org.kayura.mybatis.annotation.querier.Like;

import javax.validation.constraints.NotBlank;

public class ModuleQuery {

  @NotBlank
  private String appId;
  private String parentId;
  @IsNull(value = "parentId")
  private boolean parentIsNull;
  @Like("code,name")
  private String searchText;

  public String getAppId() {
    return appId;
  }

  public ModuleQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ModuleQuery setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public boolean isParentIsNull() {
    return parentIsNull;
  }

  public ModuleQuery setParentIsNull(boolean parentIsNull) {
    this.parentIsNull = parentIsNull;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public ModuleQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
