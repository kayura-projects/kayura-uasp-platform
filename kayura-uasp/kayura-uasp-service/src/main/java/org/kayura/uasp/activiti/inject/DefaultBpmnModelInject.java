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

package org.kayura.uasp.activiti.inject;

import org.kayura.uasp.activiti.utils.ActivitiHelper;
import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.except.ExceptUtils;
import org.kayura.utils.Assert;
import org.kayura.utils.StringUtils;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultBpmnModelInject implements BpmnModelInject, UaspBpmnConstants {

  @Override
  public void handle(BpmnModel bpmnModel) {

    Process mainProcess = bpmnModel.getMainProcess();
    mainProcess.setExecutable(Boolean.TRUE);

    this.validate(mainProcess);
    this.inject(mainProcess);
  }

  protected void validate(Process mainProcess) {

    List<StartEvent> startEvents = mainProcess.findFlowElementsOfType(StartEvent.class);
    Assert.notEmpty(startEvents, "至少需要一个【开始】节点。");
    Assert.isTrue(startEvents.size() == 1, "只允许有一个【开始】节点。");

    List<EndEvent> endEvents = mainProcess.findFlowElementsOfType(EndEvent.class);
    Assert.notEmpty(endEvents, "至少需要一个【结束】节点。");

    List<UserTask> userTasks = mainProcess.findFlowElementsOfType(UserTask.class);
    Assert.notEmpty(userTasks, "至少需要一个【用户任务】节点。");

    // 节点元素都要有连接线
    List<FlowNode> flowNodes = mainProcess.findFlowElementsOfType(FlowNode.class);
    List<FlowNode> lackFlows = flowNodes.stream().filter(x -> {
      if (x instanceof StartEvent) {
        return x.getOutgoingFlows().isEmpty();
      } else if (x instanceof EndEvent) {
        return x.getIncomingFlows().isEmpty();
      } else {
        return x.getOutgoingFlows().isEmpty() || x.getIncomingFlows().isEmpty();
      }
    }).toList();
    if (!lackFlows.isEmpty()) {
      ExceptUtils.config("请检查设计图，发现 " + lackFlows.size() + " 个节点缺少连接线。");
    }

    // 所有 userTask 只能一条进入线
    long count = userTasks.stream().filter(x -> x.getIncomingFlows().size() > 1).count();
    if (count > 0) {
      ExceptUtils.config("请检查设计图，发现 " + count + " 个【用户任务】存在多个接入线。");
    }

    // 检查是否有重复连接的线
    Set<String> lines = new HashSet<>();
    List<SequenceFlow> sequenceFlows = mainProcess.findFlowElementsOfType(SequenceFlow.class);
    for (SequenceFlow sequenceFlow : sequenceFlows) {
      String lineKey = sequenceFlow.getSourceRef() + "__" + sequenceFlow.getTargetRef();
      if (lines.contains(lineKey)) {
        FlowElement source = sequenceFlow.getSourceFlowElement();
        FlowElement target = sequenceFlow.getTargetFlowElement();
        ExceptUtils.config("节点【" + source.getName() + "】至【" + target.getName() + "】，发现重复连接线。");
      } else {
        lines.add(lineKey);
      }
    }

  }

  protected void inject(Process mainProcess) {

    // 设置用户任务的候选人表达式
    List<UserTask> userTasks = mainProcess.findFlowElementsOfType(UserTask.class);
    for (UserTask userTask : userTasks) {
      String handleModeValue = ActivitiHelper.readValue(userTask, HANDLE_MODE, HANDLE_MODE_ANY);
      if (HANDLE_MODE_ANY.equals(handleModeValue)) {
        String candidateUsers = ActivitiHelper.readValue(userTask, CANDIDATE_USERS);
        String candidateGroups = ActivitiHelper.readValue(userTask, CANDIDATE_GROUPS);
        if (StringUtils.hasText(candidateUsers) || StringUtils.isBlank(candidateGroups)) {
          userTask.setCandidateUsers(List.of(ActivitiHelper.nextTaskUserIds(userTask.getId())));
        }
        if (StringUtils.hasText(candidateGroups)) {
          userTask.setCandidateGroups(List.of(ActivitiHelper.nextTaskGroupIds(userTask.getId())));
        }
      } else if (HANDLE_MODE_SINGLE.equals(handleModeValue)) {
        userTask.setCandidateUsers(List.of(ActivitiHelper.nextTaskUserIds(userTask.getId())));
      } else {
        MultiInstanceLoopCharacteristics multi = new MultiInstanceLoopCharacteristics();
        multi.setInputDataItem(ActivitiHelper.nextTaskUserIds(userTask.getId()));
        multi.setElementVariable("multiAssignee");
        multi.setCompletionCondition("${nrOfActiveInstances == nrOfInstances}");
        multi.setSequential("sequence".equalsIgnoreCase(handleModeValue));
        userTask.setAssignee("${multiAssignee}");
        userTask.setLoopCharacteristics(multi);
      }
    }

    // 设置连接线的条件
    List<Gateway> gateways = mainProcess.findFlowElementsOfType(Gateway.class)
      .stream().filter(x -> x.getIncomingFlows().size() == 1).toList();
    for (Gateway gateway : gateways) {
      List<SequenceFlow> outgoingFlows = gateway.getOutgoingFlows();
      if (outgoingFlows.size() > 1) {
        for (SequenceFlow sequenceFlow : outgoingFlows) {
          String targetRef = sequenceFlow.getTargetRef();
          FlowElement flowElement = mainProcess.getFlowElement(targetRef);
          if (flowElement instanceof UserTask) {
            sequenceFlow.setConditionExpression(ActivitiHelper.nextTaskContains(flowElement.getId()));
          }
        }
      }
    }
  }

}
