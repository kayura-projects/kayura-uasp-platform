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
