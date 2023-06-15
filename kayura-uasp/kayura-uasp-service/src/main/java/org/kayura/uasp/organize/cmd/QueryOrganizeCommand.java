package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.organize.OrganizeQuery;

public class QueryOrganizeCommand extends Command {

  private OrganizeQuery query;
  private PageClause pageClause;

  public OrganizeQuery getQuery() {
    return query;
  }

  public QueryOrganizeCommand setQuery(OrganizeQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryOrganizeCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
