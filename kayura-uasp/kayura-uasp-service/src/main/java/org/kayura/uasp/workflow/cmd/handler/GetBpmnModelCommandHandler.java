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
