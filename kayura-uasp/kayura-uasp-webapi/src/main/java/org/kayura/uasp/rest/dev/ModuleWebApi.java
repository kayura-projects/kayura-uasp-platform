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
package org.kayura.uasp.rest.dev;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.basic.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.func.ImportVo;
import org.kayura.uasp.func.ModulePayload;
import org.kayura.uasp.func.ModuleQuery;
import org.kayura.uasp.func.ModuleTypes;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_MODULE)
public class ModuleWebApi {

  private final CommandGateway commandGateway;

  public ModuleWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/module/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplic(ChooseApplicCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.SELECT));
  }

  @GetMapping("/module/choose/actions")
  @Secured(actions = QUERY)
  public HttpResult candidateActions(ChooseDictItemCommand command) {

    return commandGateway.send(command
      .setDefine(UaspConstants.UASP_FUNC_ACTION)
    );
  }

  @GetMapping("/module/choose/import")
  @Secured(actions = QUERY)
  public HttpResult chooseImport(ChooseModuleCommand command,
                                 String appId) {

    return commandGateway.send(command.setAppId(appId).setTypes(ModuleTypes.Category, ModuleTypes.Module));
  }

  @GetMapping("/module/tree")
  @Secured(actions = QUERY)
  public HttpResult selectCategoryTree(ChooseModuleCommand command,
                                       String appId) {

    return commandGateway.send(command.setAppId(appId).setTypes(ModuleTypes.Category));
  }

  @PostMapping("/module/page")
  @Secured(actions = QUERY)
  public HttpResult queryModulePage(QueryModuleCommand command,
                                    @RequestBody @Validated ModuleQuery query,
                                    PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/module/get")
  @Secured(actions = QUERY)
  public HttpResult findModuleById(GetModuleCommand command,
                                   @RequestParam("id") String moduleId) {

    return commandGateway.send(command.setModuleId(moduleId));
  }

  @PostMapping("/module/create")
  @Secured(actions = CREATE)
  public HttpResult createModule(CreateModuleCommand command,
                                 @RequestBody @Validated(Create.class) ModulePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/module/import")
  @Secured(actions = CREATE)
  public HttpResult importModule(ImportModuleCommand command,
                                 @RequestBody ImportVo model) {

    return commandGateway.send(command
      .setTargetAppId(model.getAppId())
      .setTargetParentId(model.getParentId())
      .setSourceModuleIds(model.getModuleIds())
    );
  }

  @PostMapping("/module/update")
  @Secured(actions = UPDATE)
  public HttpResult updateModule(UpdateModuleCommand command,
                                 @RequestBody @Validated(Update.class) ModulePayload payload) {

    return commandGateway.send(command
      .setModuleId(payload.getModuleId())
      .setPayload(payload)
    );
  }

  @PostMapping("/module/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteModule(DeleteModuleCommand command,
                                 @RequestBody @Validated IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
