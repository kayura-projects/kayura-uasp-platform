/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
