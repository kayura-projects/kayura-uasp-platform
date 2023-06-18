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
