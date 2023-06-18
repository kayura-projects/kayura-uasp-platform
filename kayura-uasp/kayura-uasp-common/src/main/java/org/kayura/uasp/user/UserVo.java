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
