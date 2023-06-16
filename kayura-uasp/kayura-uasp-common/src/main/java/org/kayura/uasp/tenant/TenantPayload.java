/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
