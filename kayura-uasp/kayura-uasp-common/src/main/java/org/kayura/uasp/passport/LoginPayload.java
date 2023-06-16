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

package org.kayura.uasp.passport;

import javax.validation.constraints.NotBlank;

public class LoginPayload {

  private String appCode;
  @NotBlank(message = "账号不允许为空。")
  private String username;
  @NotBlank(message = "密码不允许为空。")
  private String password;
  private LoginTypes type = LoginTypes.PWD;
  private String ucode;
  private String vcode;

  public String getUsername() {
    return username;
  }

  public LoginPayload setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public LoginPayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public LoginTypes getType() {
    return type;
  }

  public LoginPayload setType(LoginTypes type) {
    this.type = type;
    return this;
  }

  public String getUcode() {
    return ucode;
  }

  public LoginPayload setUcode(String ucode) {
    this.ucode = ucode;
    return this;
  }

  public String getVcode() {
    return vcode;
  }

  public LoginPayload setVcode(String vcode) {
    this.vcode = vcode;
    return this;
  }

  public String getAppCode() {
    return appCode;
  }

  public LoginPayload setAppCode(String appCode) {
    this.appCode = appCode;
    return this;
  }
}
