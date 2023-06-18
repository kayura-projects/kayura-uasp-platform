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

import org.kayura.type.DataStatus;
import org.kayura.uasp.organize.LeaderVo;

import java.time.LocalDateTime;
import java.util.List;

public class CompanyVo {

  private String companyId;
  private String parentId;
  private String treePath;
  private String tenantId;
  private CompanyTypes type;
  private String categoryId;
  private String categoryName;
  private String code;
  private String shortName;
  private String fullName;
  private String contract;
  private String mobile;
  private String address;
  private String postCode;
  private String tel;
  private String fax;
  private String email;
  private Integer sort;
  private String location;
  private String creatorId;
  private LocalDateTime createTime;
  private String updaterId;
  private LocalDateTime updateTime;
  private DataStatus status;
  private String remark;
  private List<LeaderVo> leaders;

  public String getCompanyId() {
    return companyId;
  }

  public CompanyVo setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public CompanyVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getTreePath() {
    return treePath;
  }

  public CompanyVo setTreePath(String treePath) {
    this.treePath = treePath;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CompanyVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public CompanyTypes getType() {
    return type;
  }

  public CompanyVo setType(CompanyTypes type) {
    this.type = type;
    return this;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public CompanyVo setCategoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public CompanyVo setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CompanyVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public CompanyVo setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public CompanyVo setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public CompanyVo setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getPostCode() {
    return postCode;
  }

  public CompanyVo setPostCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  public String getContract() {
    return contract;
  }

  public CompanyVo setContract(String contract) {
    this.contract = contract;
    return this;
  }

  public String getTel() {
    return tel;
  }

  public CompanyVo setTel(String tel) {
    this.tel = tel;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public CompanyVo setEmail(String email) {
    this.email = email;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public CompanyVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getLocation() {
    return location;
  }

  public CompanyVo setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public CompanyVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public CompanyVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public CompanyVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public CompanyVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public CompanyVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public CompanyVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<LeaderVo> getLeaders() {
    return leaders;
  }

  public CompanyVo setLeaders(List<LeaderVo> leaders) {
    this.leaders = leaders;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public CompanyVo setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public CompanyVo setFax(String fax) {
    this.fax = fax;
    return this;
  }
}
