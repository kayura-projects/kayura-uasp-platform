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

public class GetBpmnXmlCommand extends Command {

  private String flowId;
  private String modelId;
  private String defineId;

  public String getFlowId() {
    return flowId;
  }

  public GetBpmnXmlCommand setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  public String getModelId() {
    return modelId;
  }

  public GetBpmnXmlCommand setModelId(String modelId) {
    this.modelId = modelId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public GetBpmnXmlCommand setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }
}
