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