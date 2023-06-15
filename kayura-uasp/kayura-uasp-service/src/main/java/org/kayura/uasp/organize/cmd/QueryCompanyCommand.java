package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.company.CompanyQuery;

public class QueryCompanyCommand extends Command {

  private CompanyQuery query;
  private PageClause pageClause;

  public CompanyQuery getQuery() {
    return query;
  }

  public QueryCompanyCommand setQuery(CompanyQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryCompanyCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
