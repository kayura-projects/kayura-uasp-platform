package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.tenant.TenantQuery;

public class QueryTenantCommand extends Command {

  private PageClause pageClause;
  private OrderByClause orderByClause;
  private TenantQuery query;

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryTenantCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public QueryTenantCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }

  public TenantQuery getQuery() {
    return query;
  }

  public QueryTenantCommand setQuery(TenantQuery query) {
    this.query = query;
    return this;
  }
}
