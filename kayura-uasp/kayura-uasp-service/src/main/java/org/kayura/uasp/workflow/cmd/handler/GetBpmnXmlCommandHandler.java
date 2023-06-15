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
import org.kayura.uasp.workflow.cmd.GetBpmnXmlCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.utils.Assert;
import org.kayura.utils.StringUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class GetBpmnXmlCommandHandler implements CommandHandler<GetBpmnXmlCommand, HttpResult> {

  private final FormFlowManager flowManager;
  private final RepositoryService repositoryService;

  public GetBpmnXmlCommandHandler(FormFlowManager flowManager,
                                  RepositoryService repositoryService) {
    this.flowManager = flowManager;
    this.repositoryService = repositoryService;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetBpmnXmlCommand command) {

    BpmnXmlPayload payload;

    String defineId = command.getDefineId();
    if (StringUtils.hasText(defineId)) {
      payload = this.findDeployXml(defineId);
    } else {
      String modelId = command.getModelId();
      if (StringUtils.isBlank(modelId)) {
        String flowId = command.getFlowId();
        if (StringUtils.isBlank(flowId)) {
          return HttpResult.error("缺少查询条件。");
        }
        FormFlowEntity entity = flowManager.selectById(flowId);
        Assert.notNull(entity, "查找的流程定义不存在。");

        Model model = repositoryService.createModelQuery()
          .modelTenantId(entity.getTenantId())
          .modelKey(entity.getProcessKey())
          .singleResult();
        modelId = model.getId();
      }
      payload = this.findModelXml(modelId);
    }

    return HttpResult.okBody(payload);
  }

  protected BpmnXmlPayload findModelXml(String modelId) {

    byte[] bytes = repositoryService.getModelEditorSource(modelId);
    String xml = new String(bytes, StandardCharsets.UTF_8);
    return BpmnXmlPayload.create().setId(modelId).setBody(xml);
  }

  protected BpmnXmlPayload findDeployXml(String defineId) {

    ProcessDefinition definition = repositoryService.getProcessDefinition(defineId);
    definition.getDeploymentId();
    InputStream resource = repositoryService.getResourceAsStream(definition.getDeploymentId(), definition.getResourceName());
    BpmnXmlPayload payload = BpmnXmlPayload.create().setId(defineId);
    try {
      payload.setBody(IOUtils.toString(resource, StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return payload;
  }

}
