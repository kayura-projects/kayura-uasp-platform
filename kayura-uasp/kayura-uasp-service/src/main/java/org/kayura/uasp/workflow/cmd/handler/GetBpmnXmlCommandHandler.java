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
