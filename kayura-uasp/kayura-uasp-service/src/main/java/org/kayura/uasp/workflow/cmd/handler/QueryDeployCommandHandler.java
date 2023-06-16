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

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.activiti.manage.HistoryProcInstManager;
import org.kayura.uasp.activiti.model.ProcInstCount;
import org.kayura.uasp.workflow.HistoryDeployVo;
import org.kayura.uasp.workflow.cmd.QueryDeployCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.utils.DateUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryDeployCommandHandler implements CommandHandler<QueryDeployCommand, HttpResult> {

  private final FormFlowManager flowManager;
  private final RepositoryService repositoryService;
  private final HistoryProcInstManager historyProcInstManager;

  public QueryDeployCommandHandler(FormFlowManager flowManager,
                                   RepositoryService repositoryService,
                                   HistoryProcInstManager historyProcInstManager) {
    this.flowManager = flowManager;
    this.repositoryService = repositoryService;
    this.historyProcInstManager = historyProcInstManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryDeployCommand command) {

    String flowId = command.getFlowId();
    FormFlowEntity flow = flowManager.selectById(flowId);
    if (flow == null) {
      return HttpResult.error("要查询的流程不存在。");
    }

    String processKey = flow.getProcessKey();
    List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery()
      .processDefinitionKey(processKey)
      .orderByProcessDefinitionVersion().desc()
      .list();
    List<Deployment> deployments = repositoryService.createDeploymentQuery()
      .processDefinitionKey(processKey)
      .orderByDeploymenTime().desc()
      .list();

    List<ProcInstCount> instCounts = historyProcInstManager.collectProcInst(Collections.singletonList(processKey));

    List<HistoryDeployVo> collect = definitions.stream().map(m -> {
      HistoryDeployVo deployVo = HistoryDeployVo.create()
        .setDefineId(m.getId())
        .setDeploymentId(m.getDeploymentId())
        .setName(m.getName())
        .setVersion(m.getVersion())
        .setInstances(0);
      // DeployTime
      deployments.stream()
        .filter(x -> x.getId().equals(m.getDeploymentId()))
        .findFirst()
        .ifPresent(value -> deployVo.setDeployTime(DateUtils.toLocalDateTime(value.getDeploymentTime())));
      // Count
      instCounts.stream()
        .filter(x -> x.getDefineId().equals(m.getId()))
        .findFirst()
        .ifPresent(value -> deployVo.setInstances(value.getCount()));
      return deployVo;
    }).collect(Collectors.toList());

    return HttpResult.okBody(collect);
  }
}
