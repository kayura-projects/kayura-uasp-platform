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

package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.func.ModuleTypes;

import java.util.Set;

public class ChooseModuleCommand extends Command {

  private String appId;
  private Set<ModuleTypes> types;

  public String getAppId() {
    return appId;
  }

  public ChooseModuleCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Set<ModuleTypes> getTypes() {
    return types;
  }

  public ChooseModuleCommand setTypes(Set<ModuleTypes> types) {
    this.types = types;
    return this;
  }

  public ChooseModuleCommand setTypes(ModuleTypes... types) {
    this.types = Set.of(types);
    return this;
  }
}
