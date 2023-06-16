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
