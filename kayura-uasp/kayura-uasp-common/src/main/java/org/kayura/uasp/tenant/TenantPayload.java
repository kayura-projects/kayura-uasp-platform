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

package org.kayura.uasp.tenant;

import org.kayura.type.UsableStatus;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TenantPayload {

  @NotBlank(groups = Update.class)
  private String tenantId;
  @NotBlank
  private String code;
  @NotBlank
  private String name;
  private LocalDate expireTime;

  @NotBlank(groups = Update.class)
  private String adminId;
  @NotBlank(groups = Update.class, message = "管理员“显示名”不能为空")
  private String adminName;
  @NotBlank(groups = Update.class, message = "管理员“帐号”不能为空")
  private String adminCode;
  private String adminMobile;
  private String adminEmail;
  private String adminPwd;

  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;

  private UsableStatus status;
  private String remark;

  public String getTenantId() {
    return tenantId;
  }

  public TenantPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getName() {
    return name;
  }

  public TenantPayload setName(String name) {
    this.name = name;
    return this;
  }

  public LocalDate getExpireTime() {
    return expireTime;
  }

  public TenantPayload setExpireTime(LocalDate expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public String getAdminId() {
    return adminId;
  }

  public TenantPayload setAdminId(String adminId) {
    this.adminId = adminId;
    return this;
  }

  public String getAdminCode() {
    return adminCode;
  }

  public TenantPayload setAdminCode(String adminCode) {
    this.adminCode = adminCode;
    return this;
  }

  public String getAdminMobile() {
    return adminMobile;
  }

  public TenantPayload setAdminMobile(String adminMobile) {
    this.adminMobile = adminMobile;
    return this;
  }

  public String getAdminEmail() {
    return adminEmail;
  }

  public TenantPayload setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
    return this;
  }

  public String getAdminPwd() {
    return adminPwd;
  }

  public TenantPayload setAdminPwd(String adminPwd) {
    this.adminPwd = adminPwd;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public TenantPayload setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public TenantPayload setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public TenantPayload setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public TenantPayload setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TenantPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCode() {
    return code;
  }

  public TenantPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getAdminName() {
    return adminName;
  }

  public TenantPayload setAdminName(String adminName) {
    this.adminName = adminName;
    return this;
  }
}
