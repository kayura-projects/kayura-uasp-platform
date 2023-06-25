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
