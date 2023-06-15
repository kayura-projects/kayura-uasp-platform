package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.uasp.organize.EmployeeQuery;

public class ChooseEmployeeCommand extends Command {

  private EmployeeQuery query;
  private OrderByClause orderByClause;

  public EmployeeQuery getQuery() {
    return query;
  }

  public ChooseEmployeeCommand setQuery(EmployeeQuery query) {
    this.query = query;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public ChooseEmployeeCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }
}
