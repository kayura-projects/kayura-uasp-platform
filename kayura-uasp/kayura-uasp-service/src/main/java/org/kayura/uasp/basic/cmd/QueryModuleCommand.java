package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.func.ModuleQuery;

public class QueryModuleCommand extends Command {

  private ModuleQuery query;
  private PageClause pageClause;

  public ModuleQuery getQuery() {
    return query;
  }

  public QueryModuleCommand setQuery(ModuleQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryModuleCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
