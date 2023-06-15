package org.kayura.uasp.company;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

import javax.validation.constraints.NotNull;

public class CompanyQuery {

  private String tenantId;
  private String parentId;
  private Integer level;
  @NotNull
  private CompanyTypes type;
  private String categoryId;
  @Like("code,fullName,address")
  private String searchText;
  private DataStatus status;

  public String getParentId() {
    return parentId;
  }

  public CompanyQuery setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public CompanyQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public CompanyQuery setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public CompanyTypes getType() {
    return type;
  }

  public CompanyQuery setType(CompanyTypes type) {
    this.type = type;
    return this;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public CompanyQuery setCategoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CompanyQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public CompanyQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }
}
