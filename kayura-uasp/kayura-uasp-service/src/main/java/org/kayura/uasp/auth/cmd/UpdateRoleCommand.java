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

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RolePayload;
import org.kayura.uasp.role.RoleTypes;

public class UpdateRoleCommand extends Command {

  private String roleId;
  private RolePayload payload;
  private RoleTypes roleType;

  public String getRoleId() {
    return roleId;
  }

  public UpdateRoleCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public RolePayload getPayload() {
    return payload;
  }

  public UpdateRoleCommand setPayload(RolePayload payload) {
    this.payload = payload;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public UpdateRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }
}
