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

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.UserTypes;
import org.kayura.uasp.organize.entity.CompanyEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 账号(uasp_user) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_user")
public class UserEntity {

  /** 用户ID */
  @Id
  private String userId;
  /** 租户ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc", value = "short_name_")
  private String tenantName;
  /** 登录名 */
  private String userName;
  /** 呢称 */
  private String displayName;
  /** 用户类型:ROOT,ADMIN,USER,CLIENT; */
  private UserTypes userType;
  /** 头像:文件链接ID */
  private String avatar;
  /** 密码 */
  private String password;
  /** 加密盐 */
  private String salt;
  /** 电子邮件 */
  private String email;
  /** 手机号码 */
  private String mobile;
  /** 级别 */
  private Integer level;
  /** 账号到期 */
  private LocalDate accountExpire;
  /** 密码到期 */
  private LocalDate passwordExpire;
  /** 是否可用 */
  private Boolean enabled;
  /** 是否锁定 */
  private Boolean locked;
  /** 创建人ID */
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;
  /** 修改时间 */
  private LocalDateTime updateTime;
  /** 备注 */
  private String remark;

  public static UserEntity create() {
    return new UserEntity();
  }

  public String getUserId() {
    return userId;
  }

  public UserEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public UserEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserEntity setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public UserEntity setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public UserEntity setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public UserEntity setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public UserEntity setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public UserEntity setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public UserEntity setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public UserEntity setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public LocalDate getPasswordExpire() {
    return passwordExpire;
  }

  public UserEntity setPasswordExpire(LocalDate passwordExpire) {
    this.passwordExpire = passwordExpire;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public UserEntity setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public UserEntity setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public UserEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public UserEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public UserEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public UserEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public UserEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public UserEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public UserEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }
}