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
import org.kayura.uasp.workflow.cmd.SaveBpmnModelCommand;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class SaveBpmnModelCommandHandler implements CommandHandler<SaveBpmnModelCommand, HttpResult> {

  private final RepositoryService repositoryService;

  public SaveBpmnModelCommandHandler(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Transactional
  public HttpResult execute(SaveBpmnModelCommand command) {

    BpmnXmlPayload payload = Optional.ofNullable(command.getPayload()).orElse(BpmnXmlPayload.create());
    String modelId = Optional.ofNullable(command.getModelId()).orElse(payload.getId());
    String body = Optional.ofNullable(command.getBody()).orElse(payload.getBody());

    Model model = repositoryService.getModel(modelId);
    if (model == null) {
      return HttpResult.error("要保存的流程模型不存在。");
    }

    repositoryService.saveModel(model);
    repositoryService.addModelEditorSource(modelId, body.getBytes(StandardCharsets.UTF_8));

    return HttpResult.ok();
  }
}
