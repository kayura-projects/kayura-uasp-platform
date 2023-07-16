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

package org.kayura.uasp.organize.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.UsableStatus;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.company.CompanyTypes;

import java.time.LocalDateTime;

@Table("uasp_company_applic")
public class CompanyApplicEntity implements Entity {

  /** 公司应用ID */
  @Id
  private String id;

  /** 二级应用ID */
  @ForeignKey(entity = ApplicEntity.class, alias = "ap", joinType = JoinTypes.INNER)
  private String appId;
  @RefColumn(from = "ap", value = "code_")
  private String appCode;
  @RefColumn(from = "ap", value = "name_")
  private String appName;
  @RefColumn(from = "ap", value = "level_")
  private Integer appLevel;
  @RefColumn(from = "ap", value = "sort_")
  private Integer appSort;

  /** 关联公司ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc", joinType = JoinTypes.INNER)
  private String companyId;
  @RefColumn(from = "cc", value = "code_")
  private String companyCode;
  @RefColumn(from = "cc", value = "short_name_")
  private String companyName;
  @RefColumn(from = "cc", value = "type_")
  private CompanyTypes companyType;
  @RefColumn(from = "cc")
  @ForeignKey(entity = CompanyEntity.class, alias = "tt")
  private String tenantId;
  @RefColumn(from = "tt", value = "code_")
  private String tenantCode;
  @RefColumn(from = "tt", value = "short_name_")
  private String tenantName;
  @RefColumn(from = "cc")
  @ForeignKey(entity = DictItemEntity.class, alias = "cat", pkName = "item_id_")
  private String categoryId;
  @RefColumn(from = "cat", value = "name_")
  private String categoryName;

  /** 开通人ID */
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;

  /** 到期时间 */
  private LocalDateTime expireTime;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 启用状态 */
  private UsableStatus status;

  public static CompanyApplicEntity create() {
    return new CompanyApplicEntity();
  }

  public String getId() {
    return id;
  }

  public CompanyApplicEntity setId(String id) {
    this.id = id;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public CompanyApplicEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public CompanyApplicEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public Integer getAppLevel() {
    return appLevel;
  }

  public CompanyApplicEntity setAppLevel(Integer appLevel) {
    this.appLevel = appLevel;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public CompanyApplicEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public CompanyApplicEntity setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public CompanyTypes getCompanyType() {
    return companyType;
  }

  public CompanyApplicEntity setCompanyType(CompanyTypes companyType) {
    this.companyType = companyType;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CompanyApplicEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public CompanyApplicEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public CompanyApplicEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public CompanyApplicEntity setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public CompanyApplicEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public CompanyApplicEntity setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public CompanyApplicEntity setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
    return this;
  }

  public String getAppCode() {
    return appCode;
  }

  public CompanyApplicEntity setAppCode(String appCode) {
    this.appCode = appCode;
    return this;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public CompanyApplicEntity setCategoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public CompanyApplicEntity setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public Integer getAppSort() {
    return appSort;
  }

  public CompanyApplicEntity setAppSort(Integer appSort) {
    this.appSort = appSort;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public CompanyApplicEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getTenantCode() {
    return tenantCode;
  }

  public CompanyApplicEntity setTenantCode(String tenantCode) {
    this.tenantCode = tenantCode;
    return this;
  }
}
