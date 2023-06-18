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
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.workflow.BpmnXmlPayload;
import org.kayura.uasp.workflow.FormFlowPayload;
import org.kayura.uasp.workflow.FormFlowQuery;
import org.kayura.uasp.workflow.cmd.*;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_BIZ_FLOW)
public class FormFlowWebApi {

  private final CommandGateway commandGateway;

  public FormFlowWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  // form-flow

  @GetMapping("/form-flow/choose/form-tree")
  @Secured(actions = QUERY)
  public HttpResult chooseFormTree(ChooseFormTreeCommand command) {

    return commandGateway.send(command);
  }

  @GetMapping("/form-flow/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseSelectItems(ChooseTenantCommand command, String appId) {

    return commandGateway.send(command.setAppId(appId).setOutType(OutputTypes.SELECT));
  }

  @GetMapping("/form-flow/choose/inner-expr")
  @Secured(actions = QUERY)
  public HttpResult chooseInnerExpr(ChooseInnerExprCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/form-flow/page")
  @Secured(actions = QUERY)
  public HttpResult queryFlowPage(QueryFormFlowCommand command,
                                  @RequestBody FormFlowQuery query,
                                  PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/form-flow/get")
  @Secured(actions = QUERY)
  public HttpResult findFlowById(GetFormFlowCommand command,
                                 @RequestParam("id") String flowId) {

    return commandGateway.send(command.setFlowId(flowId));
  }

  @PostMapping("/form-flow/create")
  @Secured(actions = CREATE)
  public HttpResult createFlow(CreateFormFlowCommand command,
                               @RequestBody @Validated(Create.class) FormFlowPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/form-flow/update")
  @Secured(actions = UPDATE)
  public HttpResult updateFlow(UpdateFormFlowCommand command,
                               @RequestBody @Validated(Update.class) FormFlowPayload payload) {

    return commandGateway.send(command.setFlowId(payload.getFlowId()).setPayload(payload));
  }

  @PostMapping("/form-flow/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteFlow(DeleteFormFlowCommand command,
                               @RequestBody IdPayload payload) {

    return commandGateway.send(command
      .setFlowId(payload.getId())
      .setPayload(payload)
    );
  }

  @GetMapping("/form-flow/flow-label")
  @Secured(actions = QUERY)
  public HttpResult findFlowLabels(BuildFlowLabelCommand command, String flowId) {

    return commandGateway.send(command.setFlowId(flowId));
  }

  // 流程设计

  @GetMapping("/form-flow/model/get")
  @Secured(actions = QUERY)
  public HttpResult getEditModel(GetBpmnModelCommand command, String modelId) {

    return commandGateway.send(command.setModelId(modelId));
  }

  @PostMapping("/form-flow/model/save")
  @Secured(actions = {CREATE, UPDATE})
  public HttpResult saveEditModel(SaveBpmnModelCommand command,
                                  @RequestBody BpmnXmlPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/form-flow/model/deploy")
  @Secured(actions = {CREATE, UPDATE})
  public HttpResult modelDeploy(DeployBpmnModelCommand command, String flowId) {

    return commandGateway.send(command.setFlowId(flowId));
  }

  @GetMapping("/form-flow/deploy/query")
  @Secured(actions = QUERY)
  public HttpResult queryDeploy(QueryDeployCommand command,
                                String flowId) {

    return commandGateway.send(command.setFlowId(flowId));
  }

  @PostMapping("/form-flow/deploy/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteDeploy(DeleteDeployCommand command,
                                 @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @GetMapping("/form-flow/deploy/xml/get")
  @Secured(actions = QUERY)
  public HttpResult getDeployBpmnXml(GetBpmnXmlCommand command, String defineId) {

    return commandGateway.send(command.setDefineId(defineId));
  }
}
