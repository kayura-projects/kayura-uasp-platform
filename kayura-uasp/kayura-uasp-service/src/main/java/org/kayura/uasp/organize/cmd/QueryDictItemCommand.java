package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.dict.DictItemQuery;
import org.kayura.uasp.utils.OutputTypes;

public class QueryDictItemCommand extends Command {

  private OutputTypes output;
  private DictItemQuery query;
  private PageClause pageClause;

  public DictItemQuery getQuery() {
    return query;
  }

  public QueryDictItemCommand setQuery(DictItemQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryDictItemCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public OutputTypes getOutput() {
    return output;
  }

  public QueryDictItemCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }
}
