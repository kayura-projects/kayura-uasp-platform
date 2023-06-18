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
import org.kayura.uasp.role.RoleUserPayload;

import java.util.List;

public class DeleteRoleUserCommand extends Command {

  private String roleId;
  private String userId;
  private RoleUserPayload payload;
  private List<RoleUserPayload> payloads;

  public RoleUserPayload getPayload() {
    return payload;
  }

  public DeleteRoleUserCommand setPayload(RoleUserPayload payload) {
    this.payload = payload;
    return this;
  }

  public List<RoleUserPayload> getPayloads() {
    return payloads;
  }

  public DeleteRoleUserCommand setPayloads(List<RoleUserPayload> payloads) {
    this.payloads = payloads;
    return this;
  }

  public String getRoleId() {
    return roleId;
  }

  public DeleteRoleUserCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public DeleteRoleUserCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
