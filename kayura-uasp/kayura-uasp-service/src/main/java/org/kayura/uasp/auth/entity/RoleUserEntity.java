/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.UsableStatus;
import org.kayura.type.UserTypes;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.role.RoleTypes;

import java.time.LocalDateTime;

/**
 * 角色用户(uasp_role_user) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_role_user")
public class RoleUserEntity {

  /** 角色ID */
  @ForeignKey(entity = RoleEntity.class, alias = "rr")
  private String roleId;
  @RefColumn(from = "rr", value = "code_")
  private String roleCode;
  @RefColumn(from = "rr", value = "name_")
  private String roleName;
  @RefColumn(from = "rr", value = "remark_")
  private String roleRemark;
  @RefColumn(from = "rr", value = "type_")
  private RoleTypes roleType;
  @RefColumn(from = "rr", value = "status_")
  private UsableStatus roleStatus;
  @RefColumn(from = "rr", value = "app_id_")
  private String roleAppId;
  @RefColumn(from = "rr", value = "tenant_id_")
  private String roleTenantId;
  /** 用户ID:角色类型为WF时，值为身份ID； */
  @ForeignKey(entity = EmployeeEntity.class, alias = "ee")
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String userId;
  @RefColumn(from = "uu", value = "user_name_")
  private String userName;
  @RefColumn(from = "uu", value = "display_name_")
  private String userDisplayName;
  @RefColumn(from = "uu", value = "mobile_")
  private String userMobile;
  @RefColumn(from = "uu", value = "user_type_")
  private UserTypes userType;
  @RefColumn(from = "ee")
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String companyId;
  @RefColumn(from = "cc", value = "short_name_")
  private String companyName;
  /** 创建人ID */
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  /** 创建时间 */
  private LocalDateTime createTime;

  public static RoleUserEntity create() {
    return new RoleUserEntity();
  }

  public String getRoleId() {
    return roleId;
  }

  public RoleUserEntity setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public RoleUserEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public RoleUserEntity setRoleCode(String roleCode) {
    this.roleCode = roleCode;
    return this;
  }

  public String getRoleName() {
    return roleName;
  }

  public RoleUserEntity setRoleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public RoleUserEntity setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }

  public String getRoleAppId() {
    return roleAppId;
  }

  public RoleUserEntity setRoleAppId(String roleAppId) {
    this.roleAppId = roleAppId;
    return this;
  }

  public UsableStatus getRoleStatus() {
    return roleStatus;
  }

  public RoleUserEntity setRoleStatus(UsableStatus roleStatus) {
    this.roleStatus = roleStatus;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public RoleUserEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public RoleUserEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public RoleUserEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public RoleUserEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public RoleUserEntity setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public String getUserDisplayName() {
    return userDisplayName;
  }

  public RoleUserEntity setUserDisplayName(String userDisplayName) {
    this.userDisplayName = userDisplayName;
    return this;
  }

  public String getRoleRemark() {
    return roleRemark;
  }

  public RoleUserEntity setRoleRemark(String roleRemark) {
    this.roleRemark = roleRemark;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserMobile() {
    return userMobile;
  }

  public void setUserMobile(String userMobile) {
    this.userMobile = userMobile;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public void setUserType(UserTypes userType) {
    this.userType = userType;
  }

  public String getRoleTenantId() {
    return roleTenantId;
  }

  public RoleUserEntity setRoleTenantId(String roleTenantId) {
    this.roleTenantId = roleTenantId;
    return this;
  }
}