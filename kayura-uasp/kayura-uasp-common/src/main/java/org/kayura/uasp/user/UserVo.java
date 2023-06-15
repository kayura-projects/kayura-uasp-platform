package org.kayura.uasp.user;

import org.kayura.type.UserTypes;

public class UserVo {

  private String userId;
  private String userName;
  private String displayName;
  private UserTypes userType;
  private String avatar;
  private String email;
  private String mobile;

  public String getUserId() {
    return userId;
  }

  public UserVo setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserVo setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public UserVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public UserVo setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public UserVo setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserVo setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public UserVo setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }
}
