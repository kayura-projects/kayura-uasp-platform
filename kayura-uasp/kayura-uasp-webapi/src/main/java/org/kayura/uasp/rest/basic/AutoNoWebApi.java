/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.rest.basic;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.autono.AutoNoPayload;
import org.kayura.uasp.autono.AutoNoQuery;
import org.kayura.uasp.autono.NewNoArgs;
import org.kayura.uasp.basic.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_AUTO_NO)
public class AutoNoWebApi {

  private final CommandGateway commandGateway;

  public AutoNoWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  /** choose */

  @GetMapping("/auto-no/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplic(ChooseApplicCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.SELECT));
  }

  @GetMapping("/auto-no/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.SELECT));
  }

  /** define */

  @PostMapping("/auto-no/page")
  @Secured(actions = QUERY)
  public HttpResult queryAutoNoPage(QueryAutoNoConfigCommand command,
                                    PageClause pageClause,
                                    @Validated @RequestBody AutoNoQuery query) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/auto-no/get")
  @Secured(actions = QUERY)
  public HttpResult findAutoNoById(GetAutoNoConfigCommand command,
                                   @RequestParam("id") String configId) {

    return commandGateway.send(command.setConfigId(configId));
  }

  @GetMapping("/auto-no/counts")
  @Secured(actions = QUERY)
  public HttpResult findAutoNoCounts(QueryAutoNoCountCommand command,
                                     @RequestParam("id") String configId,
                                     String tenantId) {

    return commandGateway.send(command
      .setConfigId(configId)
      .setTenantId(tenantId)
    );
  }

  @PostMapping("/auto-no/create")
  @Secured(actions = CREATE)
  public HttpResult createAutoNo(CreateAutoNoConfigCommand command,
                                 @RequestBody @Validated(Create.class) AutoNoPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/auto-no/update")
  @Secured(actions = UPDATE)
  public HttpResult updateAutoNo(UpdateAutoNoConfigCommand command,
                                 @RequestBody @Validated(Update.class) AutoNoPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/auto-no/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteAutoNo(DeleteAutoNoConfigCommand command,
                                 @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/auto-no/count/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteCount(DeleteAutoNoCountCommand command,
                                @RequestParam("id") String countId) {

    return commandGateway.send(command.setCountId(countId));
  }

  @PostMapping("/auto-no/preview")
  @Secured(actions = QUERY)
  public HttpResult previewNewNo(CalcNewNoCommand command,
                                 @RequestBody @Validated NewNoArgs args) {

    return commandGateway.send(command.setArgs(args).setFixed(false));
  }

  @PostMapping("/auto-no/fixed")
  @Secured(actions = QUERY)
  public HttpResult fixedNewNo(CalcNewNoCommand command,
                               @RequestBody @Validated NewNoArgs args) {

    return commandGateway.send(command.setArgs(args).setFixed(true));
  }

}
