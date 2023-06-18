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

package org.kayura.uasp.signup;

import javax.validation.constraints.NotBlank;

public class SignupPayload {

  private String userName;
  private String displayName;
  @NotBlank(message = "手机号不允许为空。")
  private String mobile;
  @NotBlank(message = "密码不允许为空。")
  private String password;
  @NotBlank(message = "识别码不允许为空。")
  private String ucode;
  @NotBlank(message = "验证码不允许为空。")
  private String vcode;

  public String getUserName() {
    return userName;
  }

  public SignupPayload setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public SignupPayload setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public SignupPayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public SignupPayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getUcode() {
    return ucode;
  }

  public SignupPayload setUcode(String ucode) {
    this.ucode = ucode;
    return this;
  }

  public String getVcode() {
    return vcode;
  }

  public SignupPayload setVcode(String vcode) {
    this.vcode = vcode;
    return this;
  }
}
