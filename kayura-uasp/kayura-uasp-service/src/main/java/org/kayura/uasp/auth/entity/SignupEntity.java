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

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.signup.SignupStatus;

import java.time.LocalDateTime;

/**
 * 账号注册(uasp_signup) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_signup")
public class SignupEntity {

  /** 注册ID */
  @Id
  private String signupId;
  /** 手机号码 */
  private String mobile;
  /** 登录名 */
  private String userName;
  /** 呢称 */
  private String displayName;
  /** 密码 */
  private String password;
  /** 电子邮件 */
  private String email;
  /** 申请时间 */
  private LocalDateTime createTime;
  /** 状态:APPLYING 申请中,PASSED允许,REFUSED拒绝; */
  private SignupStatus status;
  /** 备注 */
  private String remark;

  public static SignupEntity create() {
    return new SignupEntity();
  }

  public String getSignupId() {
    return signupId;
  }

  public SignupEntity setSignupId(String signupId) {
    this.signupId = signupId;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public SignupEntity setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public SignupEntity setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public SignupEntity setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public SignupEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public SignupEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public SignupEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public SignupStatus getStatus() {
    return status;
  }

  public SignupEntity setStatus(SignupStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public SignupEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}