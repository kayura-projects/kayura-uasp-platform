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

package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.OrganizeTypes;

public class DeleteOrganizeCommand extends Command {

  private OrganizeTypes type;
  private String orgId;
  private IdPayload payload;

  public OrganizeTypes getType() {
    return type;
  }

  public DeleteOrganizeCommand setType(OrganizeTypes type) {
    this.type = type;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteOrganizeCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getOrgId() {
    return orgId;
  }

  public DeleteOrganizeCommand setOrgId(String orgId) {
    this.orgId = orgId;
    return this;
  }
}
