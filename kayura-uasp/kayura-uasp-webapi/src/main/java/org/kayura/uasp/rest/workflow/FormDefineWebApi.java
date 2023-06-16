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
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.form.FormDefinePayload;
import org.kayura.uasp.form.FormDefineQuery;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.workflow.cmd.*;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_BIZ_FORM)
public class FormDefineWebApi {

  private final CommandGateway commandGateway;

  public FormDefineWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/form-define/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplic(ChooseApplicCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.SELECT));
  }

  @PostMapping("/form-define/page")
  @Secured(actions = QUERY)
  public HttpResult queryFormPage(QueryFormDefineCommand command,
                                  @RequestBody FormDefineQuery query,
                                  PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/form-define/get")
  @Secured(actions = QUERY)
  public HttpResult findFormById(GetFormDefineCommand command,
                                 @RequestParam("id") String formId) {

    return commandGateway.send(command.setFormId(formId));
  }

  @PostMapping("/form-define/create")
  @Secured(actions = CREATE)
  public HttpResult createForm(CreateFormDefineCommand command,
                               @RequestBody @Validated(Create.class) FormDefinePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/form-define/update")
  @Secured(actions = UPDATE)
  public HttpResult updateForm(UpdateFormDefineCommand command,
                               @RequestBody @Validated(Update.class) FormDefinePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/form-define/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteForm(DeleteFormDefineCommand command,
                               @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
