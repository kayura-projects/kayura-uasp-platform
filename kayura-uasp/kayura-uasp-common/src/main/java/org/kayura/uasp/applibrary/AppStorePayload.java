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

package org.kayura.uasp.applibrary;

import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppStorePayload {

  @NotBlank(groups = Update.class)
  private String releaseId;
  @NotBlank(groups = Create.class)
  private String appId;
  private Integer version;
  @NotNull
  private Boolean forced;
  @NotNull
  private UpdateModes mode;
  @NotNull
  private String title;
  private String description;
  private String resourceId;
  @NotNull
  private Boolean released;
  private LocalDateTime releaseTime;

  public static AppStorePayload create() {
    return new AppStorePayload();
  }

  public String getAppId() {
    return appId;
  }

  public AppStorePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public AppStorePayload setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public Boolean getForced() {
    return forced;
  }

  public AppStorePayload setForced(Boolean forced) {
    this.forced = forced;
    return this;
  }

  public UpdateModes getMode() {
    return mode;
  }

  public AppStorePayload setMode(UpdateModes mode) {
    this.mode = mode;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public AppStorePayload setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppStorePayload setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getResourceId() {
    return resourceId;
  }

  public AppStorePayload setResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  public Boolean getReleased() {
    return released;
  }

  public AppStorePayload setReleased(Boolean released) {
    this.released = released;
    return this;
  }

  public LocalDateTime getReleaseTime() {
    return releaseTime;
  }

  public AppStorePayload setReleaseTime(LocalDateTime releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public String getReleaseId() {
    return releaseId;
  }

  public AppStorePayload setReleaseId(String releaseId) {
    this.releaseId = releaseId;
    return this;
  }
}
