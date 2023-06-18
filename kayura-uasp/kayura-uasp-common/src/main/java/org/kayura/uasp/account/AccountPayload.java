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

package org.kayura.uasp.account;

public class AccountPayload {

  private String userName;
  private String email;
  private String mobile;
  private String displayName;
  private String realName;
  private String avatar;
  private String ucode;
  private String vcode;

  public static AccountPayload create() {
    return new AccountPayload();
  }

  public String getUserName() {
    return userName;
  }

  public AccountPayload setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public AccountPayload setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public AccountPayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public AccountPayload setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public AccountPayload setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public String getUcode() {
    return ucode;
  }

  public AccountPayload setUcode(String ucode) {
    this.ucode = ucode;
    return this;
  }

  public String getVcode() {
    return vcode;
  }

  public AccountPayload setVcode(String vcode) {
    this.vcode = vcode;
    return this;
  }

  public String getRealName() {
    return realName;
  }

  public AccountPayload setRealName(String realName) {
    this.realName = realName;
    return this;
  }
}
