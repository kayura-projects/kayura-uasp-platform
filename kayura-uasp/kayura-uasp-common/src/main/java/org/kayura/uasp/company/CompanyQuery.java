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
