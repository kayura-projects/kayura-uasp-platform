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

import org.kayura.cmd.ApiCommand;
import org.kayura.uasp.workflow.BpmnXmlPayload;

public class SaveBpmnModelCommand extends ApiCommand {

  private String modelId;
  private String body;
  private BpmnXmlPayload payload;

  public String getModelId() {
    return modelId;
  }

  public SaveBpmnModelCommand setModelId(String modelId) {
    this.modelId = modelId;
    return this;
  }

  public String getBody() {
    return body;
  }

  public SaveBpmnModelCommand setBody(String body) {
    this.body = body;
    return this;
  }

  public BpmnXmlPayload getPayload() {
    return payload;
  }

  public SaveBpmnModelCommand setPayload(BpmnXmlPayload payload) {
    this.payload = payload;
    return this;
  }
}
