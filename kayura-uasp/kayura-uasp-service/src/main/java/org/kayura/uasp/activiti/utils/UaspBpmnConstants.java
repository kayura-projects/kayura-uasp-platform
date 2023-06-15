package org.kayura.uasp.activiti.utils;

import org.activiti.bpmn.constants.BpmnXMLConstants;

import java.util.Map;

public interface UaspBpmnConstants extends BpmnXMLConstants {

  String USER_TASK = "userTask";

  String CURRENT_USER_ID = "currentUserId";
  String START_USER_ID = "startUserId";
  String CURRENT_USER_DISPLAY = "当前用户";
  String START_USER_DISPLAY = "流程启动人";
  String START = "start";
  String START_DISPLAY = "开始";
  String END = "end";
  String END_DISPLAY = "结束";

  String NEXT_TASKS = "nextTasks";
  String NEXT_TASK_USER_IDS = "nextTaskUserIds__";
  String NEXT_TASK_GROUP_IDS = "nextTaskGroupIds__";

  String CHAR_SEPARATOR = ",";

  String kayura_NAMESPACE = "http://www.kayura.cloud/bpmn/model";
  String kayura_PREFIX = "kayura";

  String ASSIGNMENT_TYPE = "assignmentType"; // starter,current,candidate
  String ASSIGNMENT_TYPE_STARTER = "starter";
  String ASSIGNMENT_TYPE_CURRENT = "current";
  String ASSIGNMENT_TYPE_CANDIDATE = "candidate";

  String CANDIDATE_USERS = "candidateUsers";
  String CANDIDATE_GROUPS = "candidateGroups";

  String HANDLE_MODE = "handleMode";  // any, sequence, parallel
  String HANDLE_MODE_ANY = "any";
  String HANDLE_MODE_SINGLE = "single";
  String HANDLE_MODE_SEQUENCE = "sequence";
  String HANDLE_MODE_PARALLEL = "parallel";

  String ALLOW_BACK = "allowBack";  // true, false

  String BACK_WAY = "backWay"; // first, last, any
  String BACK_WAY_FIRST = "first";
  String BACK_WAY_LAST = "last";
  String BACK_WAY_ANY = "any";

  String ALLOW_EDIT = "allowEdit"; // true, false
  String READ_FIELDS = "readFields";
  String WRITE_FIELDS = "writeFields";

  String FLOW_LABEL = "flowLabel";
  String APPROVAL_RESULT = "approvalResult";

  Map<String, String> kayura_DEFAULT_VALUES = Map.of(
    ASSIGNMENT_TYPE, ASSIGNMENT_TYPE_CANDIDATE,
    HANDLE_MODE, HANDLE_MODE_ANY,
    ALLOW_BACK, ATTRIBUTE_VALUE_TRUE,
    BACK_WAY, BACK_WAY_ANY,
    ALLOW_EDIT, ATTRIBUTE_VALUE_FALSE
  );

  // Workflow
  String INITIAL_DIAGRAM =
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
      "<bpmn:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
      "xmlns:bpmn=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" " +
      "xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" " +
      "xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" " +
      "targetNamespace=\"http://bpmn.io/schema/bpmn\" " +
      "id=\"Definitions_1\">" +
      "<bpmn:process id=\"%s\" isExecutable=\"true\">" +
      "<bpmn:startEvent id=\"StartEvent_1\"/>" +
      "</bpmn:process>" +
      "<bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">" +
      "<bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"%s\">" +
      "<bpmndi:BPMNShape id=\"_BPMNShape_StartEvent_2\" bpmnElement=\"StartEvent_1\">" +
      "<dc:Bounds height=\"36.0\" width=\"36.0\" x=\"173.0\" y=\"102.0\"/>" +
      "</bpmndi:BPMNShape>" +
      "</bpmndi:BPMNPlane>" +
      "</bpmndi:BPMNDiagram>" +
      "</bpmn:definitions>";
}
