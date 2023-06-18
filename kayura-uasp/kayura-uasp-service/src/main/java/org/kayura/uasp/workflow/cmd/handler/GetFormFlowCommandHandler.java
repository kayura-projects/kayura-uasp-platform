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
