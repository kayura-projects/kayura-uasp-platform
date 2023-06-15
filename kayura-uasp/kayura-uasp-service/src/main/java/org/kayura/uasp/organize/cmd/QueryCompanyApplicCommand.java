package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.company.CompanyApplicQuery;

public class QueryCompanyApplicCommand extends Command {

  private String companyId;
  private String appId;
  private CompanyApplicQuery query;
  private PageClause pageClause;
  private OrderByClause orderByClause;

  public String getCompanyId() {
    return companyId;
  }

  public QueryCompanyApplicCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public QueryCompanyApplicCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public CompanyApplicQuery getQuery() {
    return query;
  }

  public QueryCompanyApplicCommand setQuery(CompanyApplicQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryCompanyApplicCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public QueryCompanyApplicCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }
}
