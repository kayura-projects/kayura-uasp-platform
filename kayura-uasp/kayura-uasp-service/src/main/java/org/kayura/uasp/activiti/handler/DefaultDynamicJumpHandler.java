package org.kayura.uasp.activiti.handler;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DefaultDynamicJumpHandler implements DynamicJumpHandler {

  private final RepositoryService repositoryService;
  private final RuntimeService runtimeService;

  public DefaultDynamicJumpHandler(RepositoryService repositoryService, RuntimeService runtimeService) {
    this.repositoryService = repositoryService;
    this.runtimeService = runtimeService;
  }

  @Override
  public void handle(TaskEntity currentTask, String targetId, Map<String, Object> variables) {

    ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery();
    ProcessInstance processInstance = instanceQuery.processInstanceId(currentTask.getProcessInstanceId())
      .singleResult();
    ProcessDefinitionEntity process = (ProcessDefinitionEntity) repositoryService
      .getProcessDefinition(processInstance.getProcessDefinitionId());

  }

}
