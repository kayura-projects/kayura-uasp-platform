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
