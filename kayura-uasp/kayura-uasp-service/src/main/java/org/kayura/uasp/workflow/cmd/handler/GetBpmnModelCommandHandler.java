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
