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
