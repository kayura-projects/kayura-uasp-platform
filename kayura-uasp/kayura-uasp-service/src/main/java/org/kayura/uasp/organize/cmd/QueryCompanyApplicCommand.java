/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
