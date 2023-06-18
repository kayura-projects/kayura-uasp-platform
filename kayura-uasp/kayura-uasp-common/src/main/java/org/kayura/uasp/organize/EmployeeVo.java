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

import org.kayura.type.UserTypes;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.user.UserAvatarVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EmployeeVo {

  private String employeeId;
  private String tenantId;
  private String companyId;
  private String companyName;
  private CompanyTypes companyType;
  private String departId;
  private String departName;
  private String jobNo;
  private String realName;
  private LocalDate birthday;
  private String nation;
  private String origin;
  private SexEnum sex;
  private String education;
  private Double height;
  private Double weight;
  private String marital;
  private String idCard;
  private String address;
  private LocalDateTime enterDate;
  private LocalDateTime overDate;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private String updaterName;
  private LocalDateTime updateTime;
  private WorkStatus status;
  private String remark;

  // ---- auth ----

  private String userName;
  private UserTypes userType;
  private String displayName;
  private String mobile;
  private String email;
  private String avatar;
  private List<UserAvatarVo> historyAvatars;
  private LocalDate accountExpire;
  private Boolean enabled;
  private Boolean locked;

  public static EmployeeVo create() {
    return new EmployeeVo();
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public EmployeeVo setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public EmployeeVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public EmployeeVo setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public EmployeeVo setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public CompanyTypes getCompanyType() {
    return companyType;
  }

  public EmployeeVo setCompanyType(CompanyTypes companyType) {
    this.companyType = companyType;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public EmployeeVo setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public EmployeeVo setDepartName(String departName) {
    this.departName = departName;
    return this;
  }

  public String getJobNo() {
    return jobNo;
  }

  public EmployeeVo setJobNo(String jobNo) {
    this.jobNo = jobNo;
    return this;
  }

  public String getRealName() {
    return realName;
  }

  public EmployeeVo setRealName(String realName) {
    this.realName = realName;
    return this;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public EmployeeVo setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public String getNation() {
    return nation;
  }

  public EmployeeVo setNation(String nation) {
    this.nation = nation;
    return this;
  }

  public String getOrigin() {
    return origin;
  }

  public EmployeeVo setOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  public SexEnum getSex() {
    return sex;
  }

  public EmployeeVo setSex(SexEnum sex) {
    this.sex = sex;
    return this;
  }

  public String getEducation() {
    return education;
  }

  public EmployeeVo setEducation(String education) {
    this.education = education;
    return this;
  }

  public Double getHeight() {
    return height;
  }

  public EmployeeVo setHeight(Double height) {
    this.height = height;
    return this;
  }

  public Double getWeight() {
    return weight;
  }

  public EmployeeVo setWeight(Double weight) {
    this.weight = weight;
    return this;
  }

  public String getMarital() {
    return marital;
  }

  public EmployeeVo setMarital(String marital) {
    this.marital = marital;
    return this;
  }

  public String getIdCard() {
    return idCard;
  }

  public EmployeeVo setIdCard(String idCard) {
    this.idCard = idCard;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public EmployeeVo setAddress(String address) {
    this.address = address;
    return this;
  }

  public LocalDateTime getEnterDate() {
    return enterDate;
  }

  public EmployeeVo setEnterDate(LocalDateTime enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDateTime getOverDate() {
    return overDate;
  }

  public EmployeeVo setOverDate(LocalDateTime overDate) {
    this.overDate = overDate;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public EmployeeVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public EmployeeVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public EmployeeVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public EmployeeVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public EmployeeVo setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public EmployeeVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public WorkStatus getStatus() {
    return status;
  }

  public EmployeeVo setStatus(WorkStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public EmployeeVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public EmployeeVo setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public EmployeeVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public EmployeeVo setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public List<UserAvatarVo> getHistoryAvatars() {
    return historyAvatars;
  }

  public EmployeeVo setHistoryAvatars(List<UserAvatarVo> historyAvatars) {
    this.historyAvatars = historyAvatars;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public EmployeeVo setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public EmployeeVo setEmail(String email) {
    this.email = email;
    return this;
  }

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public EmployeeVo setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public EmployeeVo setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public EmployeeVo setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public EmployeeVo setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }
}
