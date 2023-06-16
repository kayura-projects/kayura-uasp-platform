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
