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

package org.kayura.uasp.auth.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;

import java.time.LocalDateTime;

/**
 * 用户头像(uasp_user_avatar) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_user_avatar")
public class UserAvatarEntity implements Entity {

  /** 头像ID */
  @Id
  private String avatarId;
  /** 用户ID */
  private String userId;
  /** 图片ID */
  private String photoId;
  /** 创建人ID */
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;
  /** 创建时间 */
  @Sort(desc = true)
  private LocalDateTime updateTime;

  public static UserAvatarEntity create() {
    return new UserAvatarEntity();
  }

  public String getAvatarId() {
    return avatarId;
  }

  public UserAvatarEntity setAvatarId(String avatarId) {
    this.avatarId = avatarId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public UserAvatarEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getPhotoId() {
    return photoId;
  }

  public UserAvatarEntity setPhotoId(String photoId) {
    this.photoId = photoId;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public UserAvatarEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public UserAvatarEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public UserAvatarEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }
}