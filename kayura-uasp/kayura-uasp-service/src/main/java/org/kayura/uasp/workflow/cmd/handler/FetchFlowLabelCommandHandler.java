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
import org.kayura.uasp.activiti.manage.ActivitiManager;
import org.kayura.uasp.workflow.FlowLabel;
import org.kayura.uasp.workflow.FlowLabels;
import org.kayura.uasp.workflow.cmd.BuildFlowLabelCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.utils.CollectionUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FetchFlowLabelCommandHandler implements CommandHandler<BuildFlowLabelCommand, HttpResult> {

  private final RepositoryService repositoryService;
  private final FormFlowManager flowManager;
  private final ActivitiManager activitiManager;

  public FetchFlowLabelCommandHandler(RepositoryService repositoryService,
                                      FormFlowManager flowManager,
                                      ActivitiManager activitiManager) {
    this.repositoryService = repositoryService;
    this.flowManager = flowManager;
    this.activitiManager = activitiManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(BuildFlowLabelCommand command) {

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

    BpmnModel bpmnModel = activitiManager.convertToBpmnModel(model);

    Process mainProcess = bpmnModel.getMainProcess();
    List<UserTask> userTasks = mainProcess.findFlowElementsOfType(UserTask.class, true);
    if (CollectionUtils.isEmpty(userTasks)) {
      return HttpResult.error("设计图未发现任务节点，请检查是否已保存。");
    }

    FlowLabels flowLabels = new FlowLabels();
    for (int i = 0; i < userTasks.size(); i++) {
      UserTask m = userTasks.get(i);
      flowLabels.add(FlowLabel.create()
        .setCode("u" + RandomStringUtils.randomAlphanumeric(4).toLowerCase())
        .setName(m.getName())
        .setSort(i + 1)
      );
    }
    return HttpResult.okBody(flowLabels);
  }
}
