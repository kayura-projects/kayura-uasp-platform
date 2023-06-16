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
