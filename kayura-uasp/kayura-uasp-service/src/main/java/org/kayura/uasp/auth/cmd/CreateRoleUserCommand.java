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

public class CreateRoleUserCommand extends Command {

  private RoleUserPayload payload;
  private List<RoleUserPayload> payloads;

  public RoleUserPayload getPayload() {
    return payload;
  }

  public CreateRoleUserCommand setPayload(RoleUserPayload payload) {
    this.payload = payload;
    return this;
  }

  public List<RoleUserPayload> getPayloads() {
    return payloads;
  }

  public CreateRoleUserCommand setPayloads(List<RoleUserPayload> payloads) {
    this.payloads = payloads;
    return this;
  }
}
