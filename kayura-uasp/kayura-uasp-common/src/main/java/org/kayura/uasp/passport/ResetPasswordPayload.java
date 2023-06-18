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

public class ResetPasswordPayload {

  @NotBlank(message = "手机号不允许为空。")
  private String mobile;
  @NotBlank(message = "识别码不允许为空。")
  private String ucode;
  @NotBlank(message = "验证码不允许为空。")
  private String vcode;
  @NotBlank(message = "新密码不允许为空。")
  private String password;

  public String getMobile() {
    return mobile;
  }

  public ResetPasswordPayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getVcode() {
    return vcode;
  }

  public ResetPasswordPayload setVcode(String vcode) {
    this.vcode = vcode;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public ResetPasswordPayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getUcode() {
    return ucode;
  }

  public ResetPasswordPayload setUcode(String ucode) {
    this.ucode = ucode;
    return this;
  }
}
