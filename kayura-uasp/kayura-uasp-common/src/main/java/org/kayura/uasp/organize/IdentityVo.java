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

import java.time.LocalDate;

public class IdentityVo {

  private String identityId;
  private String employeeId;
  private String departId;
  private String departName;
  private String positionId;
  private String positionName;
  private LocalDate enterDate;
  private LocalDate overDate;
  private JobStatus status;
  private String remark;

  public String getIdentityId() {
    return identityId;
  }

  public IdentityVo setIdentityId(String identityId) {
    this.identityId = identityId;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public IdentityVo setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public IdentityVo setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public IdentityVo setDepartName(String departName) {
    this.departName = departName;
    return this;
  }

  public String getPositionId() {
    return positionId;
  }

  public IdentityVo setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public String getPositionName() {
    return positionName;
  }

  public IdentityVo setPositionName(String positionName) {
    this.positionName = positionName;
    return this;
  }

  public LocalDate getEnterDate() {
    return enterDate;
  }

  public IdentityVo setEnterDate(LocalDate enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDate getOverDate() {
    return overDate;
  }

  public IdentityVo setOverDate(LocalDate overDate) {
    this.overDate = overDate;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public IdentityVo setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public IdentityVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
