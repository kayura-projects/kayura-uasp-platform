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
