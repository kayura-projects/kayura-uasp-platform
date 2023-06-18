/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
