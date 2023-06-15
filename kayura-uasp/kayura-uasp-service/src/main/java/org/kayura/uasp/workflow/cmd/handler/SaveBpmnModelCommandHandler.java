/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
