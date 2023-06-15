package org.kayura.uasp.organize;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;
import org.kayura.uasp.company.CompanyTypes;

public class OrganizeQuery {

  private String tenantId;
  private String parentId;
  private CompanyTypes type;
  private DataStatus status;
  @Like("code,name")
  private String searchText;

  public String getParentId() {
    return parentId;
  }

  public OrganizeQuery setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public CompanyTypes getType() {
    return type;
  }

  public OrganizeQuery setType(CompanyTypes type) {
    this.type = type;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public OrganizeQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public OrganizeQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public OrganizeQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
