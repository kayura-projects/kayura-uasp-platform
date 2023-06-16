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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.workflow.cmd.DeleteFormFlowCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteFormFlowCommandHandler implements CommandHandler<DeleteFormFlowCommand, HttpResult> {

  private final FormFlowManager flowManager;
  private final RepositoryService repositoryService;

  public DeleteFormFlowCommandHandler(FormFlowManager flowManager,
                                      RepositoryService repositoryService) {
    this.flowManager = flowManager;
    this.repositoryService = repositoryService;
  }

  @Transactional
  public HttpResult execute(DeleteFormFlowCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String flowId = Optional.ofNullable(command.getFlowId()).orElse(payload.getId());

    List<FormFlowEntity> entities = null;
    if (StringUtils.hasText(flowId)) {
      entities = List.of(flowManager.selectById(flowId));
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      entities = flowManager.selectList(w -> {
        w.in(FormFlowEntity::getFlowId, payload.getIds());
      });
    }

    if (entities != null) {
      for (FormFlowEntity entity : entities) {
        this.deleteProcessByProcessKey(entity.getProcessKey());
        flowManager.deleteById(entity.getFlowId());
      }
    }

    return HttpResult.ok();
  }

  public void deleteProcessByProcessKey(String processKey) {

    List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery()
      .processDefinitionKey(processKey).list();
    for (ProcessDefinition definition : definitions) {
      repositoryService.deleteDeployment(definition.getDeploymentId(), true);
    }
    Model model = repositoryService.createModelQuery().modelKey(processKey).singleResult();
    if (model != null) {
      repositoryService.deleteModel(model.getId());
    }
  }
}
