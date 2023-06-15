package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.workflow.FormFlowQuery;

public class QueryFormFlowCommand extends Command {

  private FormFlowQuery query;
  private PageClause pageClause;

  public FormFlowQuery getQuery() {
    return query;
  }

  public QueryFormFlowCommand setQuery(FormFlowQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryFormFlowCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
