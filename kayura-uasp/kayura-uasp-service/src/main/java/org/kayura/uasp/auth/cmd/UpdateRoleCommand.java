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
