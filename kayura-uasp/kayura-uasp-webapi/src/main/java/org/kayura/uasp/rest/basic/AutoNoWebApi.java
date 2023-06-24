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
