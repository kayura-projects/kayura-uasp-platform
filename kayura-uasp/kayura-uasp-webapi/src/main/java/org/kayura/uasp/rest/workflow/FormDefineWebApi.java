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
