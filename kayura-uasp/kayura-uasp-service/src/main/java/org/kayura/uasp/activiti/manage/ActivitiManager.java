package org.kayura.uasp.activiti.manage;

import org.kayura.uasp.activiti.utils.UaspBpmnConstants;
import org.kayura.uasp.activiti.cmd.DeleteProcessCmd;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.util.io.BytesStreamSource;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Component;

@Component
public class ActivitiManager implements UaspBpmnConstants {

  private final RepositoryService repositoryService;
  private final HistoryService historyService;
  private final ManagementService managementService;

  public ActivitiManager(RepositoryService repositoryService,
                         HistoryService historyService,
                         ManagementService managementService) {
    this.repositoryService = repositoryService;
    this.historyService = historyService;
    this.managementService = managementService;
  }

  public BpmnModel convertToBpmnModel(Model model) {

    byte[] bytes = repositoryService.getModelEditorSource(model.getId());
    BytesStreamSource streamSource = new BytesStreamSource(bytes);
    BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
    BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(streamSource, true, false, "UTF-8");
    return bpmnModel;
  }

  public void deleteProcessByBusinessKey(String businessKey) {

    HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
      .processInstanceBusinessKey(businessKey)
      .singleResult();
    if (processInstance != null) {
      // delete runtime
      if (processInstance.getEndTime() == null) {
        managementService.executeCommand(DeleteProcessCmd.create()
          .setInstanceId(processInstance.getId())
          .setDeleteReason(DeleteProcessCmd.FORM_DELETED)
        );
      } else {
        // delete history
        historyService.deleteHistoricProcessInstance(processInstance.getId());
      }
    }
  }

}
