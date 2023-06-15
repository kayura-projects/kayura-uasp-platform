package org.kayura.uasp.user;

import java.time.LocalDateTime;

public class UserAvatarVo {

  private String photoId;
  private LocalDateTime updateTime;

  public static UserAvatarVo create() {
    return new UserAvatarVo();
  }

  public String getPhotoId() {
    return photoId;
  }

  public UserAvatarVo setPhotoId(String photoId) {
    this.photoId = photoId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public UserAvatarVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}
