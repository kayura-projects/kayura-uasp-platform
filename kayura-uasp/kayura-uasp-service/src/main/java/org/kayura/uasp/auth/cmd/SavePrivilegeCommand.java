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
import org.kayura.uasp.privilege.ModuleAction;
import org.kayura.uasp.privilege.PrivilegeTypes;

import java.util.List;

public class SavePrivilegeCommand extends Command {

  private String appId;
  private PrivilegeTypes type;
  private String linkId;
  private List<ModuleAction> privileges;

  public PrivilegeTypes getType() {
    return type;
  }

  public SavePrivilegeCommand setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public SavePrivilegeCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public SavePrivilegeCommand setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public SavePrivilegeCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
