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

package org.kayura.uasp.role;

import org.kayura.type.UsableStatus;
import org.kayura.type.UserTypes;

public class RoleUserVo {

  private String roleId;
  private String roleCode;
  private String roleName;
  private String roleRemark;
  private UsableStatus roleStatus;
  private String userId;
  private String userName;
  private String userDisplayName;
  private String userMobile;
  private UserTypes userType;
  private String companyName;

  public static RoleUserVo create() {
    return new RoleUserVo();
  }

  public String getRoleId() {
    return roleId;
  }

  public RoleUserVo setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getRoleName() {
    return roleName;
  }

  public RoleUserVo setRoleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public RoleUserVo setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserDisplayName() {
    return userDisplayName;
  }

  public RoleUserVo setUserDisplayName(String userDisplayName) {
    this.userDisplayName = userDisplayName;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public RoleUserVo setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public String getUserMobile() {
    return userMobile;
  }

  public RoleUserVo setUserMobile(String userMobile) {
    this.userMobile = userMobile;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public RoleUserVo setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }

  public String getRoleRemark() {
    return roleRemark;
  }

  public RoleUserVo setRoleRemark(String roleRemark) {
    this.roleRemark = roleRemark;
    return this;
  }

  public UsableStatus getRoleStatus() {
    return roleStatus;
  }

  public RoleUserVo setRoleStatus(UsableStatus roleStatus) {
    this.roleStatus = roleStatus;
    return this;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public RoleUserVo setRoleCode(String roleCode) {
    this.roleCode = roleCode;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
