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

package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.applic.ApplicQuery;

public class QueryApplicCommand extends ApiCommand {

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
