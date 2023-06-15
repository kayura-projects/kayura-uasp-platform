package org.kayura.uasp.user;

import org.kayura.vaildation.CodeValid;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AdminUserPayload {

  @NotBlank(groups = Update.class)
  private String userId;
  @NotBlank(groups = Create.class)
  @CodeValid
  private String userName;
  @NotNull
  private String displayName;
  private String avatar;
  private String password;
  private String email;
  private String mobile;
  private LocalDate accountExpire;
  private Boolean enabled;
  private Boolean locked;
  private String remark;

  public String getUserId() {
    return userId;
  }

  public AdminUserPayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public AdminUserPayload setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public AdminUserPayload setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public AdminUserPayload setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public AdminUserPayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public AdminUserPayload setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public AdminUserPayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public AdminUserPayload setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public AdminUserPayload setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public AdminUserPayload setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public AdminUserPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
