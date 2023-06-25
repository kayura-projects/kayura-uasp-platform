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

package org.kayura.uasp.activiti.listener;

import org.kayura.uasp.activiti.utils.ActivitiHelper;
import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.type.ApproveStatus;
import org.kayura.uasp.activiti.cmd.DeleteProcessCmd;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.utils.StringUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiSequenceFlowTakenEvent;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.delegate.event.impl.ActivitiProcessCancelledEventImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusAutoUpdateEventListener implements ActivitiEventListener, ApplicationContextAware {

  private ApplicationContext springContext;

  @Override
  public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
    this.springContext = applicationContext;
  }

  @Override
  public void onEvent(ActivitiEvent event) {

    FormFlowManager formFlowManager = springContext.getBean(FormFlowManager.class);
    if (event.getType().equals(ActivitiEventType.PROCESS_STARTED)) {

      ActivitiEntityEventImpl activitiEvent = (ActivitiEntityEventImpl) event;
      ExecutionEntity execution = (ExecutionEntity) activitiEvent.getEntity();
      String processKey = execution.getProcessDefinitionKey();
      String businessKey = execution.getProcessInstanceBusinessKey();
      formFlowManager.updateAuditStatus(processKey, businessKey, ApproveStatus.Audit.getValue());

    } else if (event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {

      ActivitiEntityEventImpl activitiEvent = (ActivitiEntityEventImpl) event;
      ExecutionEntity executionEntity = (ExecutionEntity) activitiEvent.getEntity();
      ProcessDefinitionEntity definition = Context.getProcessEngineConfiguration()
        .getProcessDefinitionEntityManager()
        .findById(executionEntity.getProcessDefinitionId());
      ExecutionEntity processInstance = executionEntity.getProcessInstance();
      String processKey = definition.getKey();
      String businessKey = processInstance.getBusinessKey();

      String statusVariable = executionEntity.getVariable("STATUS", String.class);
      String status = Optional.ofNullable(statusVariable).orElse(ApproveStatus.Success.getValue());
      formFlowManager.updateAuditStatus(processKey, businessKey, status);

    } else if (event.getType().equals(ActivitiEventType.PROCESS_CANCELLED)) {

      ActivitiProcessCancelledEventImpl cancelledEvent = (ActivitiProcessCancelledEventImpl) event;
      if (!DeleteProcessCmd.FORM_DELETED.equalsIgnoreCase(cancelledEvent.getReason())) {

        ExecutionEntity executionEntity = (ExecutionEntity) cancelledEvent.getEntity();
        ProcessDefinitionEntity definition = Context.getProcessEngineConfiguration()
          .getProcessDefinitionEntityManager()
          .findById(executionEntity.getProcessDefinitionId());
        ExecutionEntity processInstance = executionEntity.getProcessInstance();
        String processKey = definition.getKey();
        String businessKey = processInstance.getBusinessKey();

        formFlowManager.updateAuditStatus(processKey, businessKey, ApproveStatus.Draft.getValue());
      }

    } else if (event.getType().equals(ActivitiEventType.SEQUENCEFLOW_TAKEN)) {

      ActivitiSequenceFlowTakenEvent takenEvent = (ActivitiSequenceFlowTakenEvent) event;

      ExecutionEntity executionEntity = Context.getProcessEngineConfiguration()
        .getExecutionEntityManager().findById(event.getExecutionId());
      ExecutionEntity processInstance = executionEntity.getProcessInstance();
      RepositoryService repositoryService = Context.getProcessEngineConfiguration().getRepositoryService();
      BpmnModel bpmnModel = repositoryService.getBpmnModel(executionEntity.getProcessDefinitionId());

      String flowLabel = ActivitiHelper.readValue(bpmnModel, takenEvent.getId(), UaspBpmnConstants.FLOW_LABEL);
      if (StringUtils.isNotBlank(flowLabel)) {

        ProcessDefinitionEntity definition = Context.getProcessEngineConfiguration()
          .getProcessDefinitionEntityManager()
          .findById(executionEntity.getProcessDefinitionId());
        String processKey = definition.getKey();
        String businessKey = processInstance.getBusinessKey();

        formFlowManager.updateFlowLabel(processKey, businessKey, flowLabel);
      }

      String approvalResult = ActivitiHelper.readValue(bpmnModel, takenEvent.getId(), UaspBpmnConstants.APPROVAL_RESULT);
      if (StringUtils.isNotBlank(approvalResult)) {
        Context.getProcessEngineConfiguration().getRuntimeService().setVariable(processInstance.getId(), "STATUS", approvalResult);
      }
    }
  }

  @Override
  public boolean isFailOnException() {
    return true;
  }

}
