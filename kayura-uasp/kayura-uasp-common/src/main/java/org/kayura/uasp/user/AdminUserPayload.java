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
