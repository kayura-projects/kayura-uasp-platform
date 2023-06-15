package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.NextTaskFrm;
import org.kayura.uasp.workflow.SubmitPayload;
import org.kayura.uasp.workflow.cmd.SubmitTaskCommand;
import org.kayura.uasp.workflow.manage.OpinionManager;
import org.kayura.uasp.workflow.manage.WorkflowManager;
import org.kayura.utils.StringUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class SubmitTaskCommandHandler implements CommandHandler<SubmitTaskCommand, HttpResult> {

  private final RepositoryService repositoryService;
  private final TaskService taskService;
  private final OpinionManager opinionManager;
  private final WorkflowManager workflowManager;

  public SubmitTaskCommandHandler(RepositoryService repositoryService,
                                  TaskService taskService,
                                  OpinionManager opinionManager,
                                  WorkflowManager workflowManager) {
    this.repositoryService = repositoryService;
    this.taskService = taskService;
    this.opinionManager = opinionManager;
    this.workflowManager = workflowManager;
  }

  @Transactional
  public HttpResult execute(SubmitTaskCommand command) {

    LoginUser loginUser = command.getLoginUser();
    SubmitPayload payload = command.getPayload();

    if (loginUser.hasTenantUser()) {
      payload.setHandler(loginUser.getUserId());
    }

    if (StringUtils.isNotEmpty(payload.getTaskId())) {
      return HttpResult.error("必需指定任务键（TaskId）。");
    }
    if (StringUtils.isNotEmpty(payload.getHandler())) {
      return HttpResult.error("必需指定任务处理人（Handler）。");
    }

    TaskQuery taskQuery = taskService.createTaskQuery();
    Task currentTask = taskQuery.taskId(payload.getTaskId()).includeProcessVariables()
      .includeTaskLocalVariables().singleResult();
    if (currentTask == null) {
      return HttpResult.error("指定的任务ID不存在。");
    }

    if (StringUtils.isBlank(currentTask.getAssignee())) {
      taskService.claim(currentTask.getId(), payload.getHandler());
    } else if (!currentTask.getAssignee().equals(payload.getHandler())) {
      return HttpResult.error("不是本人的任务，不能处理。");
    }

    Authentication.setAuthenticatedUserId(payload.getHandler());
    List<NextTaskFrm> nextTasks = Optional.ofNullable(payload.getNextTasks()).orElse(List.of());
    Map<String, Object> variables = Optional.ofNullable(payload.getVariables()).orElse(Map.of());

    ProcessDefinition definition = repositoryService.getProcessDefinition(currentTask.getProcessDefinitionId());
    workflowManager.prepareSubmitPayload(definition.getKey(), nextTasks, variables);

    if (StringUtils.isNotEmpty(payload.getComment())) {
      this.addTaskComment(currentTask.getId(), payload.getHandler(), payload.getComment());
    }

    Map<String, Object> submitVariables = payload.getVariables();
    taskService.complete(payload.getTaskId(), submitVariables);

    return HttpResult.ok();
  }

  public void addTaskComment(String taskId, String userId, String comment) {
    taskService.addComment(taskId, null, null, comment);
    if (StringUtils.hasText(comment)) {
      opinionManager.insertOrUpdate(userId, comment);
    }
  }

}
