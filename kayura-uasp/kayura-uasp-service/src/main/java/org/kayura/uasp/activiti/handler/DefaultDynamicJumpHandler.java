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
