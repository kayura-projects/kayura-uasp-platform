/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
