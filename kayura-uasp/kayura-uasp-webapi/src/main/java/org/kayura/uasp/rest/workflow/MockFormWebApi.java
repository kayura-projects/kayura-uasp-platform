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
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.mockform.MockFormPayload;
import org.kayura.uasp.mockform.MockFormQuery;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.workflow.cmd.*;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_MOCK_FORM)
public class MockFormWebApi {

  private final CommandGateway commandGateway;

  public MockFormWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/mock-form/choose/form-tree")
  @Secured(actions = QUERY)
  public HttpResult chooseFormTree(ChooseFormTreeCommand command) {

    return commandGateway.send(command);
  }

  @GetMapping("/mock-form/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenants(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutType(OutputTypes.SELECT));
  }

  @PostMapping("/mock-form/page")
  @Secured(actions = QUERY)
  public HttpResult queryMockFormPage(QueryMockFormCommand command,
                                      @RequestBody MockFormQuery query,
                                      PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/mock-form/get")
  @Secured(actions = QUERY)
  public HttpResult findMockFormById(GetMockFormCommand command,
                                     @RequestParam("id") String mockId) {

    return commandGateway.send(command.setMockId(mockId));
  }

  @PostMapping("/mock-form/create")
  @Secured(actions = CREATE)
  public HttpResult createMockForm(CreateMockFormCommand command,
                                   @RequestBody @Validated(Create.class) MockFormPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/mock-form/update")
  @Secured(actions = UPDATE)
  public HttpResult updateMockForm(UpdateMockFormCommand command,
                                   @RequestBody @Validated(Update.class) MockFormPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/mock-form/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteMockForm(DeleteMockFormCommand command,
                                   @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
