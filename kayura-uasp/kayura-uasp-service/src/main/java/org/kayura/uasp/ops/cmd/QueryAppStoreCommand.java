package org.kayura.uasp.ops.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.applibrary.AppStoreQuery;

public class QueryAppStoreCommand extends Command {

  private String appId;
  private AppStoreQuery query;
  private PageClause pageClause;

  public AppStoreQuery getQuery() {
    return query;
  }

  public QueryAppStoreCommand setQuery(AppStoreQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryAppStoreCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public QueryAppStoreCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
