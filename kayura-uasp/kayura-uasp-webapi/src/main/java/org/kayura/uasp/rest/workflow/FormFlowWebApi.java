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
