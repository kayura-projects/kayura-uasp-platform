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

package org.kayura.uasp.auth.entity;

import org.kayura.data.Entity;
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
public class SignupEntity implements Entity {

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