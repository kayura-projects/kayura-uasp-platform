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
