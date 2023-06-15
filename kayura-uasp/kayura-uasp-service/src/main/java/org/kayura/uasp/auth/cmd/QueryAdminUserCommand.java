package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.user.AdminUserQuery;

public class QueryAdminUserCommand extends Command {

  private AdminUserQuery query;
  private PageClause pageClause;

  public AdminUserQuery getQuery() {
    return query;
  }

  public QueryAdminUserCommand setQuery(AdminUserQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryAdminUserCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
