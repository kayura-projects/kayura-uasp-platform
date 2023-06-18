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

package org.kayura.uasp.organize;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.In;
import org.kayura.mybatis.annotation.querier.Like;

import java.util.List;

public class EmployeeQuery {

  @Ignore
  private String orgId;
  @Ignore
  private OrganizeTypes orgType;
  private String tenantId;
  private JobStatus status;
  @Like("realName,userName,displayName,mobile")
  private String searchText;
  @In("employeeId")
  private List<String> employeeIds;
  @In("userName")
  private List<String> userNames;

  public JobStatus getStatus() {
    return status;
  }

  public EmployeeQuery setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public EmployeeQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getOrgId() {
    return orgId;
  }

  public EmployeeQuery setOrgId(String orgId) {
    this.orgId = orgId;
    return this;
  }

  public OrganizeTypes getOrgType() {
    return orgType;
  }

  public EmployeeQuery setOrgType(OrganizeTypes orgType) {
    this.orgType = orgType;
    return this;
  }

  public List<String> getEmployeeIds() {
    return employeeIds;
  }

  public EmployeeQuery setEmployeeIds(List<String> employeeIds) {
    this.employeeIds = employeeIds;
    return this;
  }

  public List<String> getUserNames() {
    return userNames;
  }

  public EmployeeQuery setUserNames(List<String> userNames) {
    this.userNames = userNames;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public EmployeeQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
