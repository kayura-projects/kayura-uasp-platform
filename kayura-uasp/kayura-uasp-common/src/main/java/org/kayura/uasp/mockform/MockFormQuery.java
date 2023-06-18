/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
