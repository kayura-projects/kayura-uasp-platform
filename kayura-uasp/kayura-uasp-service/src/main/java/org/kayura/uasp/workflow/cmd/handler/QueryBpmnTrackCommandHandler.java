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
import org.kayura.uasp.workflow.BpmnXmlPayload;
import org.kayura.uasp.workflow.BpmnTrackVo;
import org.kayura.uasp.workflow.cmd.QueryBpmnTrackCommand;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryBpmnTrackCommandHandler
  implements CommandHandler<QueryBpmnTrackCommand, HttpResult>, UaspBpmnConstants {

  private final HistoryService historyService;
  private final RepositoryService repositoryService;

  public QueryBpmnTrackCommandHandler(HistoryService historyService,
                                      RepositoryService repositoryService) {
    this.historyService = historyService;
    this.repositoryService = repositoryService;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryBpmnTrackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String businessKey = command.getBusinessKey();

    HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
      .processInstanceBusinessKey(businessKey).singleResult();
    if (hpi == null) {
      return HttpResult.error("该业务表单没有工作流审批记录。");
    }
    BpmnTrackVo bpmnTrack = BpmnTrackVo.create();

    BpmnXmlPayload deployXml = this.findDeployXml(hpi.getProcessDefinitionId());
    bpmnTrack.setBpmnXml(deployXml.getBody());

    List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery()
      .processInstanceId(hpi.getId())
      .orderByHistoricActivityInstanceStartTime().asc().list();

    List<String> currents = activityInstances.stream()
      .filter(x -> USER_TASK.equals(x.getActivityType()) && x.getEndTime() == null)
      .map(HistoricActivityInstance::getActivityId).distinct().toList();
    bpmnTrack.setCurrents(currents);

    List<String> completes = new ArrayList<>(activityInstances.stream()
      .filter(x -> x.getEndTime() != null)
      .map(HistoricActivityInstance::getActivityId).distinct().toList());
    completes.removeAll(currents);
    bpmnTrack.setCompletes(completes);

    BpmnModel bpmnModel = repositoryService.getBpmnModel(hpi.getProcessDefinitionId());
    List<String> activityIds = activityInstances.stream()
      .map(HistoricActivityInstance::getActivityId).distinct().toList();
    bpmnTrack.setFlows(this.fetchThroughFlows(bpmnModel, activityIds, completes, currents));

    return HttpResult.okBody(bpmnTrack);
  }

  protected List<String> fetchThroughFlows(BpmnModel bpmnModel, List<String> activityIds, List<String> completes, List<String> currents) {

    List<String> flows = new ArrayList<>();
    for (String node : activityIds) {

      FlowElement flowElement = bpmnModel.getFlowElement(node);
      if (flowElement != null) {
        if (flowElement instanceof FlowNode flowNode) {
          List<SequenceFlow> incomingFlows = flowNode.getIncomingFlows();
          for (SequenceFlow flow : incomingFlows) {
            if (completes.contains(flow.getSourceRef()) && !flows.contains(flow.getId())) {
              flows.add(flow.getId());
            }
          }
          List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();
          for (SequenceFlow flow : outgoingFlows) {
            String target = flow.getTargetRef();
            if ((completes.contains(target) || currents.contains(target)) && !flows.contains(flow.getId())) {
              flows.add(flow.getId());
            }
          }
        }
      }
    }

    return flows;
  }

  public BpmnXmlPayload findDeployXml(String defineId) {

    ProcessDefinition definition = repositoryService.getProcessDefinition(defineId);
    definition.getDeploymentId();
    InputStream resource = repositoryService.getResourceAsStream(definition.getDeploymentId(), definition.getResourceName());
    BpmnXmlPayload payload = BpmnXmlPayload.create().setId(defineId);
    try {
      payload.setBody(IOUtils.toString(resource, StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return payload;
  }

}
