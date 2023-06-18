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
