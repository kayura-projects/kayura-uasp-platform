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
import org.kayura.uasp.role.RoleUserQuery;
import org.kayura.uasp.utils.OutputTypes;

public class QueryRoleUserCommand extends ApiCommand {

  private OutputTypes output;
  private String roleId;
  private String userId;
  private RoleUserQuery query;
  private PageClause pageClause;
  private OrderByClause orderByClause;

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryRoleUserCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public QueryRoleUserCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }

  public RoleUserQuery getQuery() {
    return query;
  }

  public QueryRoleUserCommand setQuery(RoleUserQuery query) {
    this.query = query;
    return this;
  }

  public String getRoleId() {
    return roleId;
  }

  public QueryRoleUserCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public QueryRoleUserCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public OutputTypes getOutput() {
    return output;
  }

  public QueryRoleUserCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }
}
