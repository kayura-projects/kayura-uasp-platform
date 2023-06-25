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

package org.kayura.uasp.ops.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.uasp.applibrary.UpdateModes;
import org.kayura.uasp.dev.entity.ApplicEntity;

import java.time.LocalDateTime;

/**
 * APP发布(uasp_app_store) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_app_store")
public class AppStoreEntity {

  /** 发布ID */
  @Id
  private String releaseId;

  /** 应用ID */
  @ForeignKey(entity = ApplicEntity.class, alias = "app")
  private String appId;
  @RefColumn(from = "app", value = "code_")
  private String appCode;
  @RefColumn(from = "app", value = "name_")
  private String appName;
  /** 版本号 */
  @Sort(desc = true)
  private Integer version;
  /** 强制更新 */
  private Boolean forced;
  /** 更新方式;A增量;F整包; */
  private UpdateModes mode;
  /** 标题 */
  private String title;
  /** 描述 */
  private String description;
  /** 包文件ID */
  private String resourceId;
  /** 已发布 */
  private Boolean released;
  /** 发布时间 */
  private LocalDateTime releaseTime;
  /** 创建人ID */
  private String creatorId;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  private String updaterId;
  /** 修改时间 */
  private LocalDateTime updateTime;

  public static AppStoreEntity create() {
    return new AppStoreEntity();
  }

  public String getReleaseId() {
    return releaseId;
  }

  public AppStoreEntity setReleaseId(String releaseId) {
    this.releaseId = releaseId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public AppStoreEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public AppStoreEntity setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public Boolean getForced() {
    return forced;
  }

  public AppStoreEntity setForced(Boolean forced) {
    this.forced = forced;
    return this;
  }

  public UpdateModes getMode() {
    return mode;
  }

  public AppStoreEntity setMode(UpdateModes mode) {
    this.mode = mode;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public AppStoreEntity setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppStoreEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getResourceId() {
    return resourceId;
  }

  public AppStoreEntity setResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  public Boolean getReleased() {
    return released;
  }

  public AppStoreEntity setReleased(Boolean released) {
    this.released = released;
    return this;
  }

  public LocalDateTime getReleaseTime() {
    return releaseTime;
  }

  public AppStoreEntity setReleaseTime(LocalDateTime releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public AppStoreEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public AppStoreEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public AppStoreEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public AppStoreEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getAppCode() {
    return appCode;
  }

  public AppStoreEntity setAppCode(String appCode) {
    this.appCode = appCode;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public AppStoreEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }
}