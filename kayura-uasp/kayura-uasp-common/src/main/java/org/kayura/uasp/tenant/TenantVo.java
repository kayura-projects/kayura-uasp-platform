/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.tenant;

import org.kayura.type.UsableStatus;
import org.kayura.uasp.company.CompanyApplicVo;
import org.kayura.uasp.company.CompanyVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TenantVo {

  private String tenantId;
  private String code;
  private String name;
  private LocalDate expireTime;

  private String adminId;
  private String adminCode;
  private String adminName;
  private String adminMobile;
  private String adminEmail;

  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private UsableStatus status;
  private String remark;

  private CompanyVo company;
  private List<CompanyApplicVo> applics;

  public static TenantVo create() {
    return new TenantVo();
  }

  public String getTenantId() {
    return tenantId;
  }

  public TenantVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public TenantVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public TenantVo setName(String name) {
    this.name = name;
    return this;
  }

  public LocalDate getExpireTime() {
    return expireTime;
  }

  public TenantVo setExpireTime(LocalDate expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public String getAdminId() {
    return adminId;
  }

  public TenantVo setAdminId(String adminId) {
    this.adminId = adminId;
    return this;
  }

  public String getAdminCode() {
    return adminCode;
  }

  public TenantVo setAdminCode(String adminCode) {
    this.adminCode = adminCode;
    return this;
  }

  public String getAdminName() {
    return adminName;
  }

  public TenantVo setAdminName(String adminName) {
    this.adminName = adminName;
    return this;
  }

  public String getAdminMobile() {
    return adminMobile;
  }

  public TenantVo setAdminMobile(String adminMobile) {
    this.adminMobile = adminMobile;
    return this;
  }

  public String getAdminEmail() {
    return adminEmail;
  }

  public TenantVo setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public TenantVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public TenantVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public TenantVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public TenantVo setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TenantVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public CompanyVo getCompany() {
    return company;
  }

  public TenantVo setCompany(CompanyVo company) {
    this.company = company;
    return this;
  }

  public List<CompanyApplicVo> getApplics() {
    return applics;
  }

  public TenantVo setApplics(List<CompanyApplicVo> applics) {
    this.applics = applics;
    return this;
  }
}
