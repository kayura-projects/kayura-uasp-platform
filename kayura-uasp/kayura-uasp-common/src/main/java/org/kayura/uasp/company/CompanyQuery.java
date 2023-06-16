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
