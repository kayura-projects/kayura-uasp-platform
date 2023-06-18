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
