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
