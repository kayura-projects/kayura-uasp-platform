/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
