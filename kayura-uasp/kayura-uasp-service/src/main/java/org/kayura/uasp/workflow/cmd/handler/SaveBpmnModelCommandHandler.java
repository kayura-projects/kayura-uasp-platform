/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
