package org.kayura.uasp.workflow;

import org.kayura.mybatis.annotation.querier.Like;

public class FormFlowQuery {

  private String formId;
  private String tenantId;
  @Like("displayName,description")
  private String searchText;

  public String getFormId() {
    return formId;
  }

  public FormFlowQuery setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FormFlowQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public FormFlowQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
