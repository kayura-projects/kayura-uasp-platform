package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.user.ClientUserQuery;

public class QueryClientUserCommand extends Command {

  private ClientUserQuery query;
  private PageClause pageClause;

  public ClientUserQuery getQuery() {
    return query;
  }

  public QueryClientUserCommand setQuery(ClientUserQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryClientUserCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
