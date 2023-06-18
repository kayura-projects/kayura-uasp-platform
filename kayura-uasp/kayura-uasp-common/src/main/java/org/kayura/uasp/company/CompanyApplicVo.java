/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
