/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
