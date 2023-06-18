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
package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteDeployCommand extends Command {

  private String deployId;
  private IdPayload payload;
  private boolean cascade;

  public String getDeployId() {
    return deployId;
  }

  public DeleteDeployCommand setDeployId(String deployId) {
    this.deployId = deployId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteDeployCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public boolean isCascade() {
    return cascade;
  }

  public DeleteDeployCommand setCascade(boolean cascade) {
    this.cascade = cascade;
    return this;
  }
}
