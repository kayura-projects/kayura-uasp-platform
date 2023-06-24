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
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.role.*;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_WF_ROLE)
public class FlowRoleWebApi {

  private final CommandGateway commandGateway;

  public FlowRoleWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/wf-role/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenants(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.TREE));
  }

  @GetMapping("/wf-role/choose/tenant-applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplicByRole(ChooseApplicCommand command,
                                       String tenantId) {

    return commandGateway.send(command
      .setTenantId(tenantId)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @PostMapping("/wf-role/page")
  @Secured(actions = QUERY)
  public HttpResult queryRolePage(QueryRoleCommand command,
                                  @RequestBody RoleQuery query,
                                  PageClause pageClause) {

    return commandGateway.send(command
      .setRoleType(RoleTypes.WORK_FLOW)
      .setAppId(query.getAppId())
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/wf-role/get")
  @Secured(actions = QUERY)
  public HttpResult findRoleById(GetRoleCommand command,
                                 @RequestParam("id") String roleId) {

    return commandGateway.send(command.setRoleId(roleId));
  }

  @PostMapping("/wf-role/create")
  @Secured(actions = CREATE)
  public HttpResult createRole(CreateRoleCommand command,
                               @RequestBody @Validated(Create.class) RolePayload payload) {

    return commandGateway.send(command
      .setRoleType(RoleTypes.WORK_FLOW)
      .setPayload(payload)
    );
  }

  @PostMapping("/wf-role/update")
  @Secured(actions = UPDATE)
  public HttpResult updateRole(UpdateRoleCommand command,
                               @RequestBody @Validated(Update.class) RolePayload payload) {

    return commandGateway.send(command
      .setRoleId(payload.getRoleId())
      .setPayload(payload)
    );
  }

  @PostMapping("/wf-role/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRole(DeleteRoleCommand command,
                               @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  // ---- Role User ----

  @GetMapping("/wf-role/choose/user")
  @Secured(actions = QUERY)
  public HttpResult chooseUsers(ChooseUserCommand command,
                                String roleId) {

    return commandGateway.send(command
      .setRoleId(roleId)
      .setUserTypes(List.of(UserTypes.USER, UserTypes.MANAGER))
    );
  }

  @PostMapping("/wf-role/user/page")
  @Secured(actions = QUERY)
  public HttpResult selectRoleUserPage(QueryRoleUserCommand command,
                                       String roleId,
                                       @RequestBody RoleUserQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command
      .setRoleId(roleId)
      .setQuery(query)
      .setPageClause(pageClause)
      .setOutput(OutputTypes.USER)
    );
  }

  @PostMapping("/wf-role/user/create")
  @Secured(actions = CREATE)
  public HttpResult batchCreateRoleUser(CreateRoleUserCommand command,
                                        @RequestBody @Validated(Create.class) List<RoleUserPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/wf-role/user/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRoleUser(DeleteRoleUserCommand command,
                                   @RequestBody RoleUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
