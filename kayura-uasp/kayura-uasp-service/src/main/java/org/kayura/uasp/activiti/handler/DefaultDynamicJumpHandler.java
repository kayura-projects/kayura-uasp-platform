/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
