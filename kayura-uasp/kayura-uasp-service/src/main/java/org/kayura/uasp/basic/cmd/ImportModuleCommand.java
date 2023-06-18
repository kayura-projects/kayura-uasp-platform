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

import java.util.List;

public class ImportModuleCommand extends Command {

  private String targetAppId;
  private String targetParentId;
  private List<String> sourceModuleIds;

  public String getTargetAppId() {
    return targetAppId;
  }

  public ImportModuleCommand setTargetAppId(String targetAppId) {
    this.targetAppId = targetAppId;
    return this;
  }

  public String getTargetParentId() {
    return targetParentId;
  }

  public ImportModuleCommand setTargetParentId(String targetParentId) {
    this.targetParentId = targetParentId;
    return this;
  }

  public List<String> getSourceModuleIds() {
    return sourceModuleIds;
  }

  public ImportModuleCommand setSourceModuleIds(List<String> sourceModuleIds) {
    this.sourceModuleIds = sourceModuleIds;
    return this;
  }
}
