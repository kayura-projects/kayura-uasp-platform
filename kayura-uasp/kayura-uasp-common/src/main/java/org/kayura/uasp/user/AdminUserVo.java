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

package org.kayura.uasp.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AdminUserVo extends UserVo {

  private LocalDate accountExpire;
  private Boolean enabled;
  private Boolean locked;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private String updaterName;
  private LocalDateTime updateTime;
  private String remark;
  // avatar list
  private List<UserAvatarVo> historyAvatars;

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public AdminUserVo setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public AdminUserVo setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public AdminUserVo setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public AdminUserVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public AdminUserVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public AdminUserVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public AdminUserVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public AdminUserVo setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public AdminUserVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public AdminUserVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<UserAvatarVo> getHistoryAvatars() {
    return historyAvatars;
  }

  public AdminUserVo setHistoryAvatars(List<UserAvatarVo> historyAvatars) {
    this.historyAvatars = historyAvatars;
    return this;
  }
}
