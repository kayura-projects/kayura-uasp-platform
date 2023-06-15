package org.kayura.uasp.workflow.manage;

import org.kayura.uasp.activiti.utils.ActivitiHelper;
import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.except.ExceptUtils;
import org.kayura.security.utils.UserContext;
import org.kayura.type.Properties;
import org.kayura.uasp.workflow.NextTaskFrm;
import org.kayura.utils.CollectionUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WorkflowManager implements UaspBpmnConstants {

  private final RepositoryService repositoryService;

  public WorkflowManager(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  public void prepareSubmitPayload(String processKey, List<NextTaskFrm> nextTasks, Map<String, Object> variables) {

    if (CollectionUtils.isNotEmpty(nextTasks)) {
      variables.put(NEXT_TASKS, nextTasks.stream().map(NextTaskFrm::getNodeId).collect(Collectors.toList()));

      ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
        .processDefinitionKey(processKey).latestVersion().singleResult();
      BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
      for (NextTaskFrm nextTask : nextTasks) {
        List<String> userIds = Optional.ofNullable(nextTask.getUserIds()).orElse(List.of());
        List<String> groupIds = Optional.ofNullable(nextTask.getGroupIds()).orElse(List.of());
        if ((userIds.size() + groupIds.size()) == 0) {
          ExceptUtils.business("【" + nextTask.getNodeId() + "】 至少要指定一个处理者。");
        }
        Properties properties = ActivitiHelper.readProperties(bpmnModel, nextTask.getNodeId());
        if (properties.containsKey(CANDIDATE_USERS) || UserContext.hasRootOrAdmin()) {
          variables.put(NEXT_TASK_USER_IDS + nextTask.getNodeId(), userIds);
        }
        if (properties.containsKey(CANDIDATE_GROUPS)) {
          variables.put(NEXT_TASK_GROUP_IDS + nextTask.getNodeId(), groupIds);
        }
      }
    }
  }

}
