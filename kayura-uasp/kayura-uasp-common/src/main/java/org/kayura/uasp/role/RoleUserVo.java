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
