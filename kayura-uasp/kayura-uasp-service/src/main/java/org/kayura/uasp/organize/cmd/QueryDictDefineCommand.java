package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.dict.DictDefineQuery;

public class QueryDictDefineCommand extends Command {

  private DictDefineQuery query;
  private PageClause pageClause;

  public DictDefineQuery getQuery() {
    return query;
  }

  public QueryDictDefineCommand setQuery(DictDefineQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryDictDefineCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
