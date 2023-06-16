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

package org.kayura.uasp.company;

import org.kayura.type.UsableStatus;

import java.time.LocalDateTime;

public class CompanyApplicVo {

  private String id;
  private String appId;
  private String appName;
  private String appLevel;
  private String companyId;
  private String companyCode;
  private String companyName;
  private CompanyTypes companyType;
  private String tenantId;
  private String tenantName;
  private LocalDateTime expireTime;
  private LocalDateTime createTime;
  private String creatorId;
  private String creatorName;
  private UsableStatus status;

  public static CompanyApplicVo create() {
    return new CompanyApplicVo();
  }

  public String getId() {
    return id;
  }

  public CompanyApplicVo setId(String id) {
    this.id = id;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public CompanyApplicVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public CompanyApplicVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public CompanyApplicVo setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public CompanyApplicVo setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public CompanyApplicVo setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public CompanyApplicVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public CompanyApplicVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public CompanyApplicVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public CompanyApplicVo setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public CompanyApplicVo setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
    return this;
  }

  public String getAppLevel() {
    return appLevel;
  }

  public CompanyApplicVo setAppLevel(String appLevel) {
    this.appLevel = appLevel;
    return this;
  }

  public CompanyTypes getCompanyType() {
    return companyType;
  }

  public CompanyApplicVo setCompanyType(CompanyTypes companyType) {
    this.companyType = companyType;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CompanyApplicVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public CompanyApplicVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }
}
