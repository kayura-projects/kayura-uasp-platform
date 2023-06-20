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

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.role.RoleQuery;
import org.kayura.uasp.role.RoleTypes;

public class QueryRoleCommand extends ApiCommand {

  private RoleTypes roleType;
  private String appId;
  private String tenantId;
  private RoleQuery query;
  private PageClause pageClause;
  private OrderByClause orderByClause;

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryRoleCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public QueryRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }

  public RoleQuery getQuery() {
    return query;
  }

  public QueryRoleCommand setQuery(RoleQuery query) {
    this.query = query;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public QueryRoleCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public QueryRoleCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public QueryRoleCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
