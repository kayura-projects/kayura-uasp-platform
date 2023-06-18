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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.BpmnXmlPayload;
import org.kayura.uasp.workflow.cmd.GetBpmnModelCommand;
import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Component
public class GetBpmnModelCommandHandler implements CommandHandler<GetBpmnModelCommand, HttpResult> {

  private final RepositoryService repositoryService;

  public GetBpmnModelCommandHandler(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetBpmnModelCommand command) {

    String modelId = command.getModelId();

    byte[] bytes = repositoryService.getModelEditorSource(modelId);
    if (bytes == null) {
      return HttpResult.error("指定的 modelId 不存在。");
    }

    String xml = new String(bytes, StandardCharsets.UTF_8);
    BpmnXmlPayload model = BpmnXmlPayload.create().setId(modelId).setBody(xml);
    return HttpResult.okBody(model);
  }
}
