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

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.role.RoleUserQuery;
import org.kayura.uasp.utils.OutputTypes;

public class QueryRoleUserCommand extends Command {

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
