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

import java.time.LocalDateTime;

public class SignupVo {

  private String signupId;
  private String userName;
  private String displayName;
  private String mobile;
  private String password;
  private String email;
  private LocalDateTime createTime;
  private SignupStatus status;
  private String remark;

  public static SignupVo create() {
    return new SignupVo();
  }

  public String getSignupId() {
    return signupId;
  }

  public SignupVo setSignupId(String signupId) {
    this.signupId = signupId;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public SignupVo setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public SignupVo setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public SignupVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public SignupVo setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public SignupVo setEmail(String email) {
    this.email = email;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public SignupVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public SignupStatus getStatus() {
    return status;
  }

  public SignupVo setStatus(SignupStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public SignupVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

}
