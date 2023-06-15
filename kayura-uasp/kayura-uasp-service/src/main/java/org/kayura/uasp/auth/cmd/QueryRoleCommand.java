package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.role.RoleQuery;
import org.kayura.uasp.role.RoleTypes;

public class QueryRoleCommand extends Command {

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
