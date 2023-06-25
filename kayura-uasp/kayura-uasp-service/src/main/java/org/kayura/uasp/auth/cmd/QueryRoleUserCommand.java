/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
