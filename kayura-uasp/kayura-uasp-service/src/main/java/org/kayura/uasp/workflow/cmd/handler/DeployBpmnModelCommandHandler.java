/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.uasp.activiti.inject.BpmnModelInject;
import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.activiti.manage.ActivitiManager;
import org.kayura.uasp.workflow.cmd.DeployBpmnModelCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeployBpmnModelCommandHandler implements CommandHandler<DeployBpmnModelCommand, HttpResult> {

  private final FormFlowManager flowManager;
  private final RepositoryService repositoryService;
  private final ActivitiManager activitiManager;
  private final BpmnModelInject bpmnModelInject;

  public DeployBpmnModelCommandHandler(FormFlowManager flowManager,
                                       RepositoryService repositoryService,
                                       ActivitiManager activitiManager,
                                       BpmnModelInject bpmnModelInject) {
    this.flowManager = flowManager;
    this.repositoryService = repositoryService;
    this.activitiManager = activitiManager;
    this.bpmnModelInject = bpmnModelInject;
  }

  @Transactional
  public HttpResult execute(DeployBpmnModelCommand command) {

    String flowId = command.getFlowId();

    FormFlowEntity entity = flowManager.selectById(flowId);
    if (entity == null) {
      return HttpResult.error("此业务流程尚未保存。");
    }

    Model model = repositoryService.createModelQuery()
      .modelTenantId(entity.getTenantId())
      .modelKey(entity.getProcessKey())
      .singleResult();
    if (model == null) {
      return HttpResult.error("尚未创建流程设计图。");
    }

    this.deploy(model);

    return HttpResult.ok();
  }

  protected void deploy(Model model) {

    BpmnModel bpmnModel = activitiManager.convertToBpmnModel(model);

    bpmnModelInject.handle(bpmnModel);

    String processName = model.getName() + ".bpmn";
    repositoryService.createDeployment()
      .name(model.getName())
      .key(model.getKey())
      .tenantId(model.getTenantId())
      .category(model.getCategory())
      .addBpmnModel(processName, bpmnModel)
      .deploy();
  }

}
