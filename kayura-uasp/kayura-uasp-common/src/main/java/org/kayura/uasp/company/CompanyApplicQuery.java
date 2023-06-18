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

package org.kayura.uasp.company;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class CompanyApplicQuery {

  private CompanyTypes companyType;
  private DataStatus status;
  @Like("companyCode,companyName")
  private String searchCompany;
  @Like("appCode,appName")
  private String searchApp;

  public static CompanyApplicQuery create() {
    return new CompanyApplicQuery();
  }

  public DataStatus getStatus() {
    return status;
  }

  public CompanyApplicQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchCompany() {
    return searchCompany;
  }

  public CompanyApplicQuery setSearchCompany(String searchCompany) {
    this.searchCompany = searchCompany;
    return this;
  }

  public String getSearchApp() {
    return searchApp;
  }

  public CompanyApplicQuery setSearchApp(String searchApp) {
    this.searchApp = searchApp;
    return this;
  }

  public CompanyTypes getCompanyType() {
    return companyType;
  }

  public CompanyApplicQuery setCompanyType(CompanyTypes companyType) {
    this.companyType = companyType;
    return this;
  }
}
