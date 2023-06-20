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

import org.kayura.cmd.ApiCommand;
import org.kayura.type.PageClause;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.EmployeeVo;
import org.kayura.uasp.organize.entity.EmployeeEntity;

import java.util.function.Function;

public class QueryEmployeeCommand extends ApiCommand {

  private EmployeeQuery query;
  private PageClause pageClause;
  private boolean includeApplic;

  private Function<EmployeeEntity, EmployeeVo> employeeConverter;
  private Function<ApplicEntity, ApplicVo> applicConverter;

  public EmployeeQuery getQuery() {
    return query;
  }

  public QueryEmployeeCommand setQuery(EmployeeQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryEmployeeCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public boolean isIncludeApplic() {
    return includeApplic;
  }

  public QueryEmployeeCommand setIncludeApplic(boolean includeApplic) {
    this.includeApplic = includeApplic;
    return this;
  }

  public Function<EmployeeEntity, EmployeeVo> getEmployeeConverter() {
    return employeeConverter;
  }

  public QueryEmployeeCommand setEmployeeConverter(Function<EmployeeEntity, EmployeeVo> employeeConverter) {
    this.employeeConverter = employeeConverter;
    return this;
  }

  public Function<ApplicEntity, ApplicVo> getApplicConverter() {
    return applicConverter;
  }

  public QueryEmployeeCommand setApplicConverter(Function<ApplicEntity, ApplicVo> applicConverter) {
    this.applicConverter = applicConverter;
    return this;
  }
}
