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

package org.kayura.uasp.applibrary;

import java.time.LocalDateTime;

public class AppStoreVo {

  private String releaseId;
  private String appId;
  private String appName;
  private Integer version;
  private Boolean forced;
  private UpdateModes mode;
  private String title;
  private String description;
  private String resourceId;
  private Boolean released;
  private LocalDateTime releaseTime;
  private String creatorId;
  private LocalDateTime createTime;

  private String resourceName;
  private Integer downloads;

  public String getModeName() {
    return this.mode.getName();
  }

  public static AppStoreVo create() {
    return new AppStoreVo();
  }

  public String getReleaseId() {
    return releaseId;
  }

  public AppStoreVo setReleaseId(String releaseId) {
    this.releaseId = releaseId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public AppStoreVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public AppStoreVo setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public Boolean getForced() {
    return forced;
  }

  public AppStoreVo setForced(Boolean forced) {
    this.forced = forced;
    return this;
  }

  public UpdateModes getMode() {
    return mode;
  }

  public AppStoreVo setMode(UpdateModes mode) {
    this.mode = mode;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public AppStoreVo setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppStoreVo setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getResourceId() {
    return resourceId;
  }

  public AppStoreVo setResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  public Boolean getReleased() {
    return released;
  }

  public AppStoreVo setReleased(Boolean released) {
    this.released = released;
    return this;
  }

  public LocalDateTime getReleaseTime() {
    return releaseTime;
  }

  public AppStoreVo setReleaseTime(LocalDateTime releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public AppStoreVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public AppStoreVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public Integer getDownloads() {
    return downloads;
  }

  public AppStoreVo setDownloads(Integer downloads) {
    this.downloads = downloads;
    return this;
  }

  public String getResourceName() {
    return resourceName;
  }

  public AppStoreVo setResourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public AppStoreVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }
}
