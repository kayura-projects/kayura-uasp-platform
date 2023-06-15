package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.autono.AutoNoQuery;

public class QueryAutoNoConfigCommand extends Command {

  private AutoNoQuery query;
  private PageClause pageClause;

  public AutoNoQuery getQuery() {
    return query;
  }

  public QueryAutoNoConfigCommand setQuery(AutoNoQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryAutoNoConfigCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
