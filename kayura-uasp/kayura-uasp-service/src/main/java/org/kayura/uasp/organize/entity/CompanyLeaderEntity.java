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

package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.ForeignKey;
import org.kayura.mybatis.annotation.mapper.RefColumn;
import org.kayura.mybatis.annotation.mapper.Table;

@Table("uasp_company_leader")
public class CompanyLeaderEntity {

  private String companyId;
  @ForeignKey(entity = EmployeeEntity.class, alias = "ee")
  private String leaderId;
  @RefColumn(from = "ee", value = "real_name_")
  private String leaderName;
  private String duty;

  public static CompanyLeaderEntity create() {
    return new CompanyLeaderEntity();
  }

  public String getCompanyId() {
    return companyId;
  }

  public CompanyLeaderEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getLeaderId() {
    return leaderId;
  }

  public CompanyLeaderEntity setLeaderId(String leaderId) {
    this.leaderId = leaderId;
    return this;
  }

  public String getDuty() {
    return duty;
  }

  public CompanyLeaderEntity setDuty(String duty) {
    this.duty = duty;
    return this;
  }

  public String getLeaderName() {
    return leaderName;
  }

  public CompanyLeaderEntity setLeaderName(String leaderName) {
    this.leaderName = leaderName;
    return this;
  }
}
