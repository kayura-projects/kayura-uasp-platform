package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.organize.IdentityQuery;

public class QueryIdentityCommand extends Command {

  private String employeeId;
  private IdentityQuery query;
  private PageClause pageClause;
  private OrderByClause orderByClause;

  public IdentityQuery getQuery() {
    return query;
  }

  public QueryIdentityCommand setQuery(IdentityQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryIdentityCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public QueryIdentityCommand setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public QueryIdentityCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }
}
