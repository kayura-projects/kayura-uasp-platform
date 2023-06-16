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
