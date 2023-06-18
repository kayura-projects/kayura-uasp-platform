/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
