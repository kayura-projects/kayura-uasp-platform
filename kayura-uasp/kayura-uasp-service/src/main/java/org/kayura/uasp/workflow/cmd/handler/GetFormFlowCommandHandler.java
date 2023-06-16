/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.FormFlowVo;
import org.kayura.uasp.workflow.cmd.GetFormFlowCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.utils.DateUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Component
public class GetFormFlowCommandHandler implements CommandHandler<GetFormFlowCommand, HttpResult> {

  private final FormFlowManager flowManager;
  private final ModelMapper modelMapper;
  private final RepositoryService repositoryService;

  public GetFormFlowCommandHandler(FormFlowManager flowManager,
                                   ModelMapper modelMapper,
                                   RepositoryService repositoryService) {
    this.flowManager = flowManager;
    this.modelMapper = modelMapper;
    this.repositoryService = repositoryService;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetFormFlowCommand command) {

    String flowId = command.getFlowId();

    FormFlowEntity entity = flowManager.selectById(flowId);
    if (entity == null) {
      return HttpResult.error("获取的记录不存在。");
    }
    FormFlowVo model = modelMapper.map(entity, FormFlowVo.class);
    this.loadBpmnModelInfo(model);
    return HttpResult.okBody(model);
  }

  protected void loadBpmnModelInfo(FormFlowVo flowVo) {

    Model model = repositoryService.createModelQuery()
      .modelTenantId(flowVo.getTenantId())
      .modelKey(flowVo.getProcessKey())
      .singleResult();
    if (model != null) {
      byte[] bytes = repositoryService.getModelEditorSource(model.getId());
      flowVo.setModelId(model.getId());
      flowVo.setLastUpdateTime(DateUtils.toLocalDateTime(model.getLastUpdateTime()));
      flowVo.setModelContent(new String(bytes, StandardCharsets.UTF_8));
    }
    Deployment lastDeploy = repositoryService.createDeploymentQuery()
      .deploymentTenantId(flowVo.getTenantId())
      .processDefinitionKey(flowVo.getProcessKey())
      .latestVersion().singleResult();
    if (lastDeploy != null) {
      flowVo.setLastDeployId(lastDeploy.getId());
      flowVo.setLastVersion(lastDeploy.getVersion());
      flowVo.setLastDeployTime(DateUtils.toLocalDateTime(lastDeploy.getDeploymentTime()));
    }

  }

}
