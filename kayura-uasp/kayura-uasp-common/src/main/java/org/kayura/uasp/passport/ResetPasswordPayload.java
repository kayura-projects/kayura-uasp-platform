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
