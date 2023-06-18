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
package org.kayura.uasp.rest.workflow;

import org.kayura.cmd.CommandGateway;
import org.kayura.type.HttpResult;
import org.kayura.uasp.workflow.AuditTrackQuery;
import org.kayura.uasp.workflow.BpmnTrackQuery;
import org.kayura.uasp.workflow.StartPayload;
import org.kayura.uasp.workflow.SubmitPayload;
import org.kayura.uasp.workflow.cmd.QueryAuditTrackCommand;
import org.kayura.uasp.workflow.cmd.QueryBpmnTrackCommand;
import org.kayura.uasp.workflow.cmd.StartProcessCommand;
import org.kayura.uasp.workflow.cmd.SubmitTaskCommand;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class WorkflowWebApi {

  private final CommandGateway commandGateway;

  public WorkflowWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/workflow/audit-track")
  public HttpResult queryAuditTrack(QueryAuditTrackCommand command,
                                    @RequestBody AuditTrackQuery query) {

    return commandGateway.send(command.setQuery(query));
  }

  @PostMapping("/workflow/bpmn-track")
  public HttpResult queryBpmnTrack(QueryBpmnTrackCommand command,
                                   @RequestBody @Validated BpmnTrackQuery query) {

    return commandGateway.send(command.setBusinessKey(query.getBusinessKey()));
  }

  @PostMapping("/workflow/process/start")
  public HttpResult startProcess(StartProcessCommand command,
                                 @RequestBody @Validated StartPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/workflow/task/submit")
  public HttpResult submitTask(SubmitTaskCommand command,
                               @RequestBody @Validated SubmitPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
