package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.uasp.activiti.utils.ActivitiHelper;
import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.security.utils.UserContext;
import org.kayura.type.HttpResult;
import org.kayura.type.IdName;
import org.kayura.type.IdNameList;
import org.kayura.type.Properties;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.organize.manage.OrganizeManager;
import org.kayura.uasp.workflow.*;
import org.kayura.uasp.workflow.cmd.QueryAuditTrackCommand;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.kayura.uasp.workflow.manage.OpinionManager;
import org.kayura.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class QueryAuditTrackCommandHandler
  implements CommandHandler<QueryAuditTrackCommand, HttpResult>, UaspBpmnConstants {

  private final HistoryService historyService;
  private final RepositoryService repositoryService;
  private final TaskService taskService;
  private final OpinionManager opinionManager;
  private final EmployeeManager employeeManager;
  private final OrganizeManager organizeManager;
  private final FormFlowManager formFlowManager;
  private final ObjectMapper objectMapper;

  protected QueryAuditTrackCommandHandler(HistoryService historyService,
                                          RepositoryService repositoryService,
                                          TaskService taskService,
                                          OpinionManager opinionManager,
                                          EmployeeManager employeeManager,
                                          OrganizeManager organizeManager,
                                          FormFlowManager formFlowManager,
                                          ObjectMapper objectMapper) {
    this.historyService = historyService;
    this.repositoryService = repositoryService;
    this.taskService = taskService;
    this.opinionManager = opinionManager;
    this.employeeManager = employeeManager;
    this.organizeManager = organizeManager;
    this.formFlowManager = formFlowManager;
    this.objectMapper = objectMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryAuditTrackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AuditTrackQuery query = command.getQuery();

    if (loginUser.hasTenantUser()) {
      query.setUserId(loginUser.getUserId());
      query.setTenantId(loginUser.getTenantId());
    } else if (StringUtils.isBlank(loginUser.getUserId())) {
      return HttpResult.error("管理账号操作时，必需指定操作员。");
    }

    String currentUserId = query.getUserId();
    AuditTrackVo auditTrack = this.queryAuditTrack(query);

    // 将人员ID找出，并查询出他们的姓名
    List<IdentityLinkVo> identityLinks = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(auditTrack.getTasks())) {
      identityLinks.addAll(
        auditTrack.getTasks().stream()
          .flatMap(m -> m.getNextTasks().stream())
          .flatMap(m -> m.getIdentityLinks().stream()).toList()
      );
      // comment used
      List<UsedOpinionVo> usedOpinionVos = opinionManager.selectTopList(currentUserId, 10);
      auditTrack.setOpinions(usedOpinionVos);
    }
    if (CollectionUtils.isNotEmpty(auditTrack.getComments())) {
      identityLinks.addAll(auditTrack.getComments().stream()
        .flatMap(m -> m.getIdentityLinks().stream()).toList()
      );
    }
    this.fetchUserGroupName(identityLinks);

    // comments
    List<TrackCommentVo> comments = auditTrack.getComments();
    if (CollectionUtils.isNotEmpty(comments)) {
      List<String> userIds = comments.stream()
        .filter(x -> StringUtils.isBlank(x.getUserName()))
        .map(TrackCommentVo::getUserId).toList();
      if (!userIds.isEmpty()) {
        Map<String, String> nameMap = employeeManager.queryIdNameMap(userIds);
        for (TrackCommentVo row : comments) {
          if (StringUtils.isBlank(row.getUserName())) {
            if (nameMap.containsKey(row.getUserId())) {
              row.setUserName(nameMap.get(row.getUserId()));
            } else {
              row.setUserName(row.getUserId());
            }
          }
        }
      }
    }

    return HttpResult.okBody(auditTrack);
  }

  protected AuditTrackVo queryAuditTrack(AuditTrackQuery query) {

    Assert.hasText(query.getBusinessKey(), "必需要指定 BusinessKey 值。");
    Assert.hasText(query.getUserId(), "必需要指定 UserId 值。");

    AuditTrackVo auditTrack = AuditTrackVo.create().setBusinessKey(query.getBusinessKey());
    String businessKey = query.getBusinessKey();

    HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
      .processInstanceBusinessKey(businessKey).singleResult();
    auditTrack.setStarted(hpi != null);
    auditTrack.setBusinessKey(businessKey);

    if (hpi == null) {
      this.fetchStartTrack(auditTrack, query);
    } else {
      this.fetchAuditTrack(auditTrack, query, hpi);
    }

    return auditTrack;
  }

  private void fetchStartTrack(AuditTrackVo auditTrack, AuditTrackQuery query) {

    Assert.hasText(query.getTenantId(), "必需要指定 TenantId 值。");

    List<ChooseFlowVo> chooseFlows = formFlowManager.chooseBizFlow(
      ChooseFlowQry.create()
        .setTenantId(query.getTenantId())
        .setFormCode(query.getFormCode())
        .setVariables(query.getVariables())
    );
    for (ChooseFlowVo flow : chooseFlows) {
      ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
        .processDefinitionKey(flow.getProcessKey())
        .latestVersion().singleResult();
      if (definition != null) {
        auditTrack.addFlow(flow);
        flow.setLastVersion(definition.getVersion());

        BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
        Process mainProcess = bpmnModel.getMainProcess();
        List<StartEvent> startEvents = mainProcess.findFlowElementsOfType(StartEvent.class);
        startEvents.stream().map(m ->
          TaskTrackVo.create()
            .setProcessKey(flow.getProcessKey())
            .setDefinitionId(definition.getId())
            .setTaskKey(m.getId())
            .setTaskName(StringUtils.isBlank(m.getName()) ? START_DISPLAY : m.getName())
            .setProperties(ActivitiHelper.readProperties(bpmnModel, m.getId()))
            .setNextTasks(this.fetchNextTasks(bpmnModel, m, null, query.getUserId(), query.getUserId(), query.getVariables()))
        ).forEach(auditTrack::addTask);
      }
    }
  }

  protected List<NextTaskVo> fetchNextTasks(BpmnModel bpmnModel, FlowNode flowNode, List<String> allTasks,
                                            String currentUserId, String startUserId,
                                            Map<String, Object> variables) {

    List<NextTaskVo> nextTasks = new ArrayList<>();
    List<UserTask> userTasks = this.findNextUserTasks(allTasks, flowNode, variables);
    for (UserTask ut : userTasks) {
      NextTaskVo nextTask = NextTaskVo.create()
        .setKey(ut.getId())
        .setName(ut.getName())
        .setProperties(ActivitiHelper.readProperties(bpmnModel, ut.getId()));
      this.fetchTaskCandidateUsers(nextTask, currentUserId, startUserId);
      nextTasks.add(nextTask);
    }
    if (nextTasks.size() == 1) {
      nextTasks.get(0).setChecked(Boolean.TRUE);
    }
    return nextTasks;
  }

  protected void fetchTaskCandidateUsers(NextTaskVo nextTask, String currentUserId, String startUserId) {

    Properties properties = nextTask.getProperties();

    String assignmentType = (String) properties.get(ASSIGNMENT_TYPE);
    if (ASSIGNMENT_TYPE_CURRENT.equalsIgnoreCase(assignmentType) || UserContext.hasRootOrAdmin()) {
      nextTask.addUser(currentUserId, null);
    }

    if (ASSIGNMENT_TYPE_STARTER.equalsIgnoreCase(assignmentType)) {
      nextTask.addUser(startUserId, null);
    } else {
      // users
      String userValue = (String) properties.get(CANDIDATE_USERS);
      if (StringUtils.hasText(userValue)) {
        try {
          IdNameList idNames = objectMapper.readValue(userValue, IdNameList.class);
          for (IdName idName : idNames) {
            nextTask.addUser(idName.getId(), idName.getName());
          }
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
        properties.remove(CANDIDATE_USERS);
      }
      // groups
      String groupValue = (String) properties.get(CANDIDATE_GROUPS);
      if (StringUtils.hasText(groupValue)) {
        try {
          IdNameList keyValues = objectMapper.readValue(groupValue, IdNameList.class);
          for (IdName idName : keyValues) {
            nextTask.addGroup(idName.getId(), idName.getName());
          }
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
      properties.remove(CANDIDATE_GROUPS);
    }
  }

  protected List<UserTask> findNextUserTasks(List<String> allTasks, FlowNode current, Map<String, Object> variables) {

    List<UserTask> nextTasks = new ArrayList<>();

    List<SequenceFlow> outgoingFlows = current.getOutgoingFlows();
    for (SequenceFlow flow : outgoingFlows) {
      FlowElement targetElement = flow.getTargetFlowElement();
      if (targetElement instanceof UserTask userTask) {
        if (this.hasTrueCondition(flow, variables)) {
          nextTasks.add(userTask);
        }
      } else if (targetElement instanceof Gateway gateway) {
        if (this.allowFetchNextTasks(allTasks, current, gateway)) {
          List<UserTask> collect = this.findNextUserTasks(allTasks, gateway, variables);
          if (!collect.isEmpty()) {
            nextTasks.addAll(collect);
          }
        }
      }
    }
    return nextTasks;
  }

  protected boolean hasTrueCondition(SequenceFlow sequenceFlow, Map<String, Object> variables) {

    return true;
  }

  protected boolean allowFetchNextTasks(List<String> allTasks, FlowNode current, FlowNode target) {

    List<UserTask> userTasks = new ArrayList<>();
    List<SequenceFlow> incomingFlows = target.getIncomingFlows();
    if (incomingFlows.size() > 1) {
      for (SequenceFlow flow : incomingFlows) {
        if (!flow.getSourceFlowElement().equals(current)) {
          FlowNode sourceFlowElement = (FlowNode) flow.getSourceFlowElement();
          if (sourceFlowElement instanceof UserTask userTask) {
            if (!userTasks.contains(userTask)) {
              userTasks.add(userTask);
            }
          }
          this.findSameSourceTasks(userTasks, sourceFlowElement);
        }
      }
    }
    List<String> sameSources = userTasks.stream().map(BaseElement::getId).toList();
    return allTasks == null || userTasks.isEmpty() || sameSources.stream().noneMatch(allTasks::contains);
  }

  protected void findSameSourceTasks(List<UserTask> userTasks, FlowNode sourceNode) {

    List<SequenceFlow> incomingFlows = sourceNode.getIncomingFlows();
    for (SequenceFlow flow : incomingFlows) {
      FlowNode sourceFlowElement = (FlowNode) flow.getSourceFlowElement();
      if (sourceFlowElement instanceof UserTask userTask) {
        if (!userTasks.contains(userTask)) {
          userTasks.add(userTask);
        }
      }
      this.findSameSourceTasks(userTasks, sourceFlowElement);
    }
  }

  protected void fetchAuditTrack(AuditTrackVo flowTrack, AuditTrackQuery query, HistoricProcessInstance hpi) {

    Assert.hasText(query.getBusinessKey(), "必需要指定 BusinessKey 值。");
    Assert.hasText(query.getUserId(), "必需要指定 UserId 值。");

    flowTrack.setEnded(hpi.getEndTime() != null);
    flowTrack.setDefinitionId(hpi.getProcessDefinitionId());
    flowTrack.setInstanceName(hpi.getName());

    // 获取所有已办、未办任务
    List<HistoricTaskInstance> allTasks = historyService.createHistoricTaskInstanceQuery()
      .processInstanceId(hpi.getId()).orderByTaskCreateTime().asc().list();

    if (Boolean.FALSE.equals(flowTrack.getEnded())) {
      // MyTasks
      flowTrack.addTasks(this.fetchMyTasks(query, hpi));
    }

    // comments
    List<Comment> comments = taskService.getProcessInstanceComments(hpi.getId());
    // start-comments
    flowTrack.addComment(this.fetchStartComment(hpi, comments));
    // complete-comments
    flowTrack.addComments(this.fetchCompleteComments(allTasks, comments));
    if (Boolean.FALSE.equals(flowTrack.getEnded())) {
      // todo comments
      flowTrack.addComments(this.fetchTodoComments(allTasks, comments));
      // not-comments
      flowTrack.addComments(this.fetchNotComments(allTasks, hpi));
    }

    // flowLabels
    flowTrack.setFlowLabels(this.fetchFlowLabels(hpi));
  }

  protected List<TaskTrackVo> fetchMyTasks(AuditTrackQuery query, HistoricProcessInstance hpi) {

    String businessKey = query.getBusinessKey();
    String currentUserId = query.getUserId();
    String startUserId = hpi.getStartUserId();

    Map<String, Object> variables = this.mergeVariables(hpi.getProcessVariables(), query.getVariables());
    BpmnModel bpmnModel = repositoryService.getBpmnModel(hpi.getProcessDefinitionId());
    List<Task> myTasks = this.taskService.createTaskQuery()
      .taskCandidateOrAssigned(currentUserId).processInstanceBusinessKey(businessKey).list();
    List<String> allTasks = this.taskService.createTaskQuery()
      .processInstanceBusinessKey(businessKey).list().stream().map(TaskInfo::getTaskDefinitionKey).toList();
    List<TaskTrackVo> taskTracks = myTasks.stream().map(m -> {
      Properties properties = ActivitiHelper.readProperties(bpmnModel, m.getTaskDefinitionKey());
      FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(m.getTaskDefinitionKey());
      List<NextTaskVo> nextTasks = this.fetchNextTasks(bpmnModel, flowNode, allTasks, currentUserId, startUserId, variables);
      TaskTrackVo trackVo = TaskTrackVo.create()
        .setTitle(hpi.getName())
        .setTaskId(m.getId())
        .setTaskKey(m.getTaskDefinitionKey())
        .setTaskName(m.getName())
        .setStartTime(DateUtils.toLocalDateTime(m.getCreateTime()))
        .setProperties(properties)
        .setNextTasks(nextTasks);
      return trackVo;
    }).toList();
    return taskTracks;
  }

  @SafeVarargs
  protected final Map<String, Object> mergeVariables(Map<String, Object>... variables) {

    Map<String, Object> map = new HashMap<>();
    if (variables != null) {
      for (Map<String, Object> item : variables) {
        if (item != null) {
          map.putAll(item);
        }
      }
    }
    return map;
  }

  protected Collection<TrackCommentVo> fetchCompleteComments(List<HistoricTaskInstance> tasks, List<Comment> comments) {

    return tasks.stream().filter(x -> x.getEndTime() != null)
      .map(m -> TrackCommentVo.create()
        .setType(CommentTypes.Complete)
        .setTaskId(m.getId())
        .setTaskKey(m.getTaskDefinitionKey())
        .setTaskName(m.getName())
        .setUserId(m.getAssignee())
        .setStartTime(DateUtils.toLocalDateTime(m.getCreateTime()))
        .setEndTime(DateUtils.toLocalDateTime(m.getEndTime()))
        .setDuration(m.getDurationInMillis())
        .setComment(this.findTaskCommentByTask(comments, m.getId(), m.getAssignee()))
      ).toList();
  }

  protected String findTaskCommentByTask(List<Comment> taskComments, String taskId, String userId) {

    Optional<String> first = taskComments.stream().filter(x -> StringUtils.equals(taskId, x.getTaskId()))
      .map(Comment::getFullMessage).findFirst();
    if (first.isPresent()) {
      return first.get();
    } else {
      List<Comment> comments = taskService.getTaskComments(taskId, CommentEntity.TYPE_COMMENT);
      Optional<Comment> first1 = comments.stream().filter(x -> Objects.equals(x.getUserId(), userId)).findFirst();
      if (first1.isPresent()) {
        return first1.get().getFullMessage();
      }
    }
    return StringUtils.EMPTY;
  }

  protected Collection<TrackCommentVo> fetchTodoComments(List<HistoricTaskInstance> tasks, List<Comment> comments) {

    return tasks.stream().filter(x -> x.getEndTime() == null)
      .map(m -> {
        TrackCommentVo tc = TrackCommentVo.create()
          .setType(CommentTypes.Todo)
          .setTaskId(m.getId())
          .setTaskKey(m.getTaskDefinitionKey())
          .setTaskName(m.getName())
          .setStartTime(DateUtils.toLocalDateTime(m.getCreateTime()));
        this.fetchCommentIdentityLinks(tc, m);
        return tc;
      }).toList();
  }

  protected void fetchCommentIdentityLinks(TrackCommentVo trackComment, HistoricTaskInstance taskInstance) {

    List<IdentityLink> links = taskService.getIdentityLinksForTask(taskInstance.getId());
    List<IdentityLinkVo> identityLinks = links.stream().map(m ->
      IdentityLinkVo.create()
        .setType(m.getType())
        .setUserId(m.getUserId())
        .setGroupId(m.getGroupId())
    ).toList();
    trackComment.setIdentityLinks(identityLinks);
  }

  protected Collection<TrackCommentVo> fetchNotComments(List<HistoricTaskInstance> tasks, HistoricProcessInstance hpi) {

    BpmnModel bpmnModel = repositoryService.getBpmnModel(hpi.getProcessDefinitionId());
    List<String> taskKeys = tasks.stream().map(TaskInfo::getTaskDefinitionKey).distinct().toList();
    List<UserTask> notTasks = bpmnModel.getMainProcess().findFlowElementsOfType(UserTask.class)
      .stream().filter(x -> !taskKeys.contains(x.getId())).toList();

    return notTasks.stream().map(m -> {
      TrackCommentVo tc = TrackCommentVo.create()
        .setType(CommentTypes.Not)
        .setTaskKey(m.getId())
        .setTaskName(m.getName());
      this.fetchUserTaskIdentityLinks(tc, ActivitiHelper.readProperties(bpmnModel, m.getId()));
      return tc;
    }).toList();
  }

  protected void fetchUserTaskIdentityLinks(TrackCommentVo trackComment, Properties properties) {

    String assignmentType = (String) properties.get(ASSIGNMENT_TYPE);
    if (ASSIGNMENT_TYPE_CURRENT.equalsIgnoreCase(assignmentType)) {
      trackComment.addUser(CURRENT_USER_ID, CURRENT_USER_DISPLAY);
    } else if (ASSIGNMENT_TYPE_STARTER.equalsIgnoreCase(assignmentType)) {
      trackComment.addUser(START_USER_ID, START_USER_DISPLAY);
    } else {
      // users
      String userValue = (String) properties.get(CANDIDATE_USERS);
      if (StringUtils.hasText(userValue)) {
        try {
          IdNameList idNames = objectMapper.readValue(userValue, IdNameList.class);
          for (IdName idName : idNames) {
            trackComment.addUser(idName.getId(), idName.getName());
          }
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
      // groups
      String groupValue = (String) properties.get(CANDIDATE_GROUPS);
      if (StringUtils.hasText(groupValue)) {
        try {
          IdNameList idNames = objectMapper.readValue(groupValue, IdNameList.class);
          for (IdName idName : idNames) {
            trackComment.addGroup(idName.getId(), idName.getName());
          }
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  protected TrackCommentVo fetchStartComment(HistoricProcessInstance hpi, List<Comment> comments) {

    String startComment = comments.stream()
      .filter(x -> START.equals(x.getType()))
      .map(Comment::getFullMessage).findAny().orElse(StringUtils.EMPTY);
    return TrackCommentVo.create()
      .setType(CommentTypes.Start)
      .setTaskKey(START)
      .setTaskName(START_DISPLAY)
      .setUserId(hpi.getStartUserId())
      .setStartTime(DateUtils.toLocalDateTime(hpi.getStartTime()))
      .setEndTime(DateUtils.toLocalDateTime(hpi.getStartTime()))
      .setComment(startComment);
  }

  protected FlowLabels fetchFlowLabels(HistoricProcessInstance hpi) {

    String processKey = hpi.getProcessDefinitionKey();
    String businessKey = hpi.getBusinessKey();

    FlowLabels flowLabels = new FlowLabels();
    String flowLabel = formFlowManager.readFlowLabel(processKey, businessKey);
    if (StringUtils.hasText(flowLabel)) {
      FormFlowEntity formFlow = formFlowManager.selectOne(w -> {
        w.eq(FormFlowEntity::getProcessKey, processKey);
      });
      if (formFlow != null) {
        flowLabels = formFlow.getFlowLabels();
        if (CollectionUtils.isEmpty(flowLabels)) {
          flowLabels = this.resolveBpmnFlowLabels(hpi.getProcessDefinitionId());
        }
        if (CollectionUtils.isNotEmpty(flowLabels)) {
          if (hpi.getEndTime() != null) {
            flowLabels.forEach(e -> e.setStatus(FlowStatus.END));
          } else {
            this.updateFlowLabelStatus(flowLabels, flowLabel);
          }
        }
      }
      // add start
      flowLabels.add(0, FlowLabel.create()
        .setCode(START)
        .setName(START_DISPLAY)
        .setStatus(FlowStatus.END)
      );
      // add end
      boolean isEnd = flowLabels.stream().allMatch(x -> FlowStatus.END.equals(x.getStatus()));
      flowLabels.add(FlowLabel.create()
        .setCode(END)
        .setName(END_DISPLAY)
        .setStatus(isEnd ? FlowStatus.END : FlowStatus.NOT)
      );
    }
    return flowLabels;
  }

  protected FlowLabels resolveBpmnFlowLabels(String processDefinitionId) {

    FlowLabels flowLabels = new FlowLabels();
    Process mainProcess = repositoryService.getBpmnModel(processDefinitionId).getMainProcess();
    List<StartEvent> startEvents = mainProcess.findFlowElementsOfType(StartEvent.class);
    for (StartEvent startEvent : startEvents) {
      this.fetchNextFlowLabels(flowLabels, startEvent);
    }
    return flowLabels;
  }

  protected void fetchNextFlowLabels(FlowLabels flowLabels, FlowNode currentNode) {

    List<SequenceFlow> outgoingFlows = currentNode.getOutgoingFlows();
    for (SequenceFlow flow : outgoingFlows) {
      FlowElement target = flow.getTargetFlowElement();
      if (target instanceof UserTask userTask) {
        flowLabels.addLabel(userTask.getId(), userTask.getName());
      }
      if (target instanceof FlowNode flowNode) {
        this.fetchNextFlowLabels(flowLabels, flowNode);
      }
    }
  }

  protected void updateFlowLabelStatus(FlowLabels flowLabels, String flowLabel) {

    flowLabels.stream().filter(x -> flowLabel.equals(x.getCode()))
      .findFirst()
      .ifPresent(label -> {
        flowLabels.stream()
          .filter(x -> x.getSort() < label.getSort())
          .forEach(row -> row.setStatus(FlowStatus.END));
        flowLabels.stream()
          .filter(x -> Objects.equals(x.getSort(), label.getSort()))
          .forEach(row -> row.setStatus(FlowStatus.NOW));
      });
  }

  protected void fetchUserGroupName(List<IdentityLinkVo> identityLinks) {

    if (CollectionUtils.isNotEmpty(identityLinks)) {

      List<IdentityLinkVo> list = identityLinks.stream()
        .filter(x -> x.isUser() && StringUtils.isBlank(x.getUserName())).toList();
      if (!list.isEmpty()) {
        List<String> ids = list.stream().map(IdentityLinkVo::getUserId).distinct().toList();
        Map<String, String> nameMap = employeeManager.queryIdNameMap(ids);
        for (IdentityLinkVo row : list) {
          if (StringUtils.isBlank(row.getUserName())) {
            if (nameMap.containsKey(row.getUserId())) {
              row.setUserName(nameMap.get(row.getUserId()));
            } else {
              row.setUserName(row.getUserId());
            }
          }
        }
      }

      list = identityLinks.stream()
        .filter(x -> x.isGroup() && StringUtils.isBlank(x.getGroupName())).toList();
      if (!list.isEmpty()) {
        List<String> ids = list.stream().map(IdentityLinkVo::getGroupId).distinct().toList();
        Map<String, String> nameMap = organizeManager.queryIdNameMap(ids);
        for (IdentityLinkVo row : list) {
          if (StringUtils.isBlank(row.getGroupName())) {
            if (nameMap.containsKey(row.getGroupId())) {
              row.setGroupName(nameMap.get(row.getGroupId()));
            } else {
              row.setGroupName(row.getGroupId());
            }
          }
        }
      }
    }
  }

}
