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
