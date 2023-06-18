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
