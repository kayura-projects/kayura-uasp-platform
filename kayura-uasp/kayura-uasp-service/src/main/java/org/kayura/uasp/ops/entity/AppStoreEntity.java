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