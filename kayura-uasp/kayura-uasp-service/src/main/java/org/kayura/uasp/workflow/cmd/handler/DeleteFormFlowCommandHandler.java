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
