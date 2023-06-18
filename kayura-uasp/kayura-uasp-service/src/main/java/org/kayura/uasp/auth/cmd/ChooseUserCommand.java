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
import org.kayura.type.UserTypes;

import java.util.List;

public class ChooseUserCommand extends Command {

  private String roleId;
  private String tenantId;
  private List<UserTypes> userTypes;

  public String getRoleId() {
    return roleId;
  }

  public ChooseUserCommand setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseUserCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public List<UserTypes> getUserTypes() {
    return userTypes;
  }

  public ChooseUserCommand setUserTypes(List<UserTypes> userTypes) {
    this.userTypes = userTypes;
    return this;
  }
}
