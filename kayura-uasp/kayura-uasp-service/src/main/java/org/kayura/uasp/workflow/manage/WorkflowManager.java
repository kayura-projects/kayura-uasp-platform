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
