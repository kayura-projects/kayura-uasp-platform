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

package org.kayura.uasp.privilege;

import java.util.List;

public class RolePrivilege {

  private String roleId;
  private String roleCode;
  private String roleName;
  private List<ModuleAction> auth;

  public static RolePrivilege create() {
    return new RolePrivilege();
  }

  public String getRoleId() {
    return roleId;
  }

  public RolePrivilege setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public RolePrivilege setRoleCode(String roleCode) {
    this.roleCode = roleCode;
    return this;
  }

  public String getRoleName() {
    return roleName;
  }

  public RolePrivilege setRoleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  public List<ModuleAction> getAuth() {
    return auth;
  }

  public RolePrivilege setAuth(List<ModuleAction> auth) {
    this.auth = auth;
    return this;
  }
}
