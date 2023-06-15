package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.file.FileLinkQuery;

public class QueryFileLinkCommand extends Command {

  private FileLinkQuery query;
  private PageClause pageClause;

  public FileLinkQuery getQuery() {
    return query;
  }

  public QueryFileLinkCommand setQuery(FileLinkQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryFileLinkCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}
