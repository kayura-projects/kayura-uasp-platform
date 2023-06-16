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
