package org.kayura.uasp.activiti.inject;

import org.activiti.bpmn.constants.BpmnXMLConstants;
import org.activiti.bpmn.model.BpmnModel;

public interface BpmnModelInject extends BpmnXMLConstants {

  void handle(BpmnModel bpmnModel);

}
