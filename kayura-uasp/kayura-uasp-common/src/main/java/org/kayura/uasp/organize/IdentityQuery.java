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

import org.kayura.mybatis.annotation.querier.Like;

import javax.validation.constraints.NotBlank;

public class IdentityQuery {

  @NotBlank
  private String employeeId;
  private JobStatus status;
  @Like("departName,positionName")
  private String searchText;

  public static IdentityQuery create() {
    return new IdentityQuery();
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public IdentityQuery setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public IdentityQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public IdentityQuery setStatus(JobStatus status) {
    this.status = status;
    return this;
  }
}
