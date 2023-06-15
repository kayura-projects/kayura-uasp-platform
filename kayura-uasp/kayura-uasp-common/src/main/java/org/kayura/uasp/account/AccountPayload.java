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
