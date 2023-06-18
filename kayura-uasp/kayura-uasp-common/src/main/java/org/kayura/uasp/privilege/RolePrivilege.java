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
