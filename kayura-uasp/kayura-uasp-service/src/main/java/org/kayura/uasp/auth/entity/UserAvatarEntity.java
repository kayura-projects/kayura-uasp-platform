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

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.*;

import java.time.LocalDateTime;

/**
 * 用户头像(uasp_user_avatar) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_user_avatar")
public class UserAvatarEntity {

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