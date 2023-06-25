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
