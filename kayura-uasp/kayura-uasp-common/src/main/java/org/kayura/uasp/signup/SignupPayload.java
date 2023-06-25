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
