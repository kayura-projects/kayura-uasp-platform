package org.kayura.uasp.mockform;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.ApproveStatus;

public class MockFormQuery {

  private String tenantId;
  private ApproveStatus status;
  @Like("code,name")
  private String searchText;

  public static MockFormQuery create() {
    return new MockFormQuery();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String tenantId;
    private ApproveStatus status;
    private String searchText;

    private Builder() {
    }

    public static Builder aMockFormQry() {
      return new Builder();
    }

    public Builder withTenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public Builder withStatus(ApproveStatus status) {
      this.status = status;
      return this;
    }

    public Builder withSearchText(String searchText) {
      this.searchText = searchText;
      return this;
    }

    public MockFormQuery build() {
      MockFormQuery mockFormQuery = new MockFormQuery();
      mockFormQuery.status = this.status;
      mockFormQuery.searchText = this.searchText;
      mockFormQuery.tenantId = this.tenantId;
      return mockFormQuery;
    }
  }

  public String getTenantId() {
    return tenantId;
  }

  public MockFormQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public ApproveStatus getStatus() {
    return status;
  }

  public MockFormQuery setStatus(ApproveStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public MockFormQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
