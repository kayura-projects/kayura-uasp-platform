package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.organize.EmployeeQuery;

public class QueryEmployeeCommand extends Command {

  private EmployeeQuery query;
  private PageClause pageClause;

  public EmployeeQuery getQuery() {
    return query;
  }

  public QueryEmployeeCommand setQuery(EmployeeQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryEmployeeCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
