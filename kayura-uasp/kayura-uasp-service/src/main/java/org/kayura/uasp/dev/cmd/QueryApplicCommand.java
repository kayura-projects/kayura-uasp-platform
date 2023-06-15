package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.applic.ApplicQuery;

public class QueryApplicCommand extends Command {

  private String companyId;
  private Boolean notUasp;
  private ApplicQuery query;
  private PageClause pageClause;
  private OrderByClause orderByClause;

  public Boolean getNotUasp() {
    return notUasp;
  }

  public QueryApplicCommand setNotUasp(Boolean notUasp) {
    this.notUasp = notUasp;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public QueryApplicCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public ApplicQuery getQuery() {
    return query;
  }

  public QueryApplicCommand setQuery(ApplicQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryApplicCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public OrderByClause getOrderByClause() {
    return orderByClause;
  }

  public QueryApplicCommand setOrderByClause(OrderByClause orderByClause) {
    this.orderByClause = orderByClause;
    return this;
  }
}
