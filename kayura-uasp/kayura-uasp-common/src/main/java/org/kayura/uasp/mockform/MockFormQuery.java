/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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
