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

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.ForeignKey;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.RefColumn;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.type.DataStatus;
import org.kayura.uasp.organize.entity.CompanyEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("uasp_tenant")
public class TenantEntity {

  @Id
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc")
  private String code;
  @RefColumn(from = "cc", value = "short_name_")
  private String name;
  @RefColumn(from = "cc")
  private DataStatus status;
  @RefColumn(from = "cc")
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  @RefColumn(from = "cc")
  private LocalDateTime createTime;

  @ForeignKey(entity = UserEntity.class, alias = "am")
  private String adminId;
  @RefColumn(from = "am", value = "user_name_")
  private String adminCode;
  @RefColumn(from = "am", value = "display_name_")
  private String adminName;
  @RefColumn(from = "am", value = "mobile_")
  private String adminMobile;
  @RefColumn(from = "am", value = "email_")
  private String adminEmail;

  private LocalDate expireTime;
  private String remark;

  public static TenantEntity create() {
    return new TenantEntity();
  }

  public String getTenantId() {
    return tenantId;
  }

  public TenantEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public TenantEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public TenantEntity setName(String name) {
    this.name = name;
    return this;
  }

  public LocalDate getExpireTime() {
    return expireTime;
  }

  public TenantEntity setExpireTime(LocalDate expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public TenantEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TenantEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public TenantEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public TenantEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public TenantEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getAdminId() {
    return adminId;
  }

  public TenantEntity setAdminId(String adminId) {
    this.adminId = adminId;
    return this;
  }

  public String getAdminCode() {
    return adminCode;
  }

  public TenantEntity setAdminCode(String adminCode) {
    this.adminCode = adminCode;
    return this;
  }

  public String getAdminName() {
    return adminName;
  }

  public TenantEntity setAdminName(String adminName) {
    this.adminName = adminName;
    return this;
  }

  public String getAdminMobile() {
    return adminMobile;
  }

  public TenantEntity setAdminMobile(String adminMobile) {
    this.adminMobile = adminMobile;
    return this;
  }

  public String getAdminEmail() {
    return adminEmail;
  }

  public TenantEntity setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
    return this;
  }
}
