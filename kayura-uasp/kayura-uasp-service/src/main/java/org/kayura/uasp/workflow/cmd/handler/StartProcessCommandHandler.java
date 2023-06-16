/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.NextTaskFrm;
import org.kayura.uasp.workflow.StartPayload;
import org.kayura.uasp.workflow.cmd.StartProcessCommand;
import org.kayura.uasp.workflow.manage.OpinionManager;
import org.kayura.uasp.workflow.manage.WorkflowManager;
import org.kayura.utils.Assert;
import org.kayura.utils.StringUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class StartProcessCommandHandler implements CommandHandler<StartProcessCommand, HttpResult>, UaspBpmnConstants {

  private final HistoryService historyService;
  private final RuntimeService runtimeService;
  private final TaskService taskService;
  private final WorkflowManager workflowManager;
  private final OpinionManager opinionManager;

  public StartProcessCommandHandler(HistoryService historyService,
                                    RuntimeService runtimeService,
                                    TaskService taskService,
                                    WorkflowManager workflowManager,
                                    OpinionManager opinionManager) {
    this.historyService = historyService;
    this.runtimeService = runtimeService;
    this.taskService = taskService;
    this.workflowManager = workflowManager;
    this.opinionManager = opinionManager;
  }

  @Transactional
  public HttpResult execute(StartProcessCommand command) {

    LoginUser loginUser = command.getLoginUser();
    StartPayload payload = command.getPayload();

    if (loginUser.hasTenantUser()) {
      payload.setTenantId(loginUser.getTenantId());
      payload.setInitiator(loginUser.getUserId());
    }

    Assert.hasText(payload.getBusinessKey(), "必需指定业务表键值（BusinessKey）。");
    Assert.hasText(payload.getProcessKey(), "必需指定启动流程的流程编号（processKey）");
    Assert.hasText(payload.getTenantId(), "启动流程时需指定租户（tenantId）。");
    Assert.hasText(payload.getInitiator(), "启动流程时需指定启动人（initiator）。");

    HistoricProcessInstanceQuery hpiQuery = historyService.createHistoricProcessInstanceQuery();
    HistoricProcessInstance hpi = hpiQuery.processInstanceBusinessKey(payload.getBusinessKey()).singleResult();
    if (hpi != null) {
      return HttpResult.error("此表单已经启动了工作流程，不允许重复启动。");
    }

    List<NextTaskFrm> nextTasks = Optional.ofNullable(payload.getNextTasks()).orElse(List.of());
    Map<String, Object> variables = Optional.ofNullable(payload.getVariables()).orElse(Map.of());

    workflowManager.prepareSubmitPayload(payload.getProcessKey(), nextTasks, variables);

    Authentication.setAuthenticatedUserId(payload.getInitiator());
    ProcessInstance instance = runtimeService.startProcessInstanceByKeyAndTenantId(payload.getProcessKey(),
      payload.getBusinessKey(), payload.getVariables(), payload.getTenantId());

    if (StringUtils.isNotBlank(payload.getComment())) {
      this.addStartComment(instance.getProcessInstanceId(), payload.getInitiator(), payload.getComment());
    }
    runtimeService.setProcessInstanceName(instance.getId(), payload.getTitle());

    return HttpResult.ok();
  }

  public void addStartComment(String instanceId, String userId, String comment) {
    taskService.addComment(null, instanceId, "start", comment);
    if (StringUtils.hasText(comment)) {
      opinionManager.insertOrUpdate(userId, comment);
    }
  }

}
