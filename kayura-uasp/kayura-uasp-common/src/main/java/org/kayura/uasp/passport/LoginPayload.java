/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
