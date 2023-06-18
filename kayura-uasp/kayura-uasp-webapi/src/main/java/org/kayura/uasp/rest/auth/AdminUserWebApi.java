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
package org.kayura.uasp.rest.auth;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.privilege.UserPrivilegePayload;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.role.RoleUserPayload;
import org.kayura.uasp.role.RoleUserQuery;
import org.kayura.uasp.user.AdminUserPayload;
import org.kayura.uasp.user.AdminUserQuery;
import org.kayura.uasp.user.ChangeUserPasswordPayload;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_ADMIN_USER)
public class AdminUserWebApi {

  private final CommandGateway commandGateway;

  public AdminUserWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/admin-user/page")
  @Secured(actions = QUERY)
  public HttpResult queryAdminUserPage(QueryAdminUserCommand command,
                                       @RequestBody @Validated AdminUserQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/admin-user/get")
  @Secured(actions = QUERY)
  public HttpResult findUserById(GetAdminUserCommand command,
                                 String id) {

    return commandGateway.send(command.setUserId(id));
  }

  @PostMapping("/admin-user/create")
  @Secured(actions = CREATE)
  public HttpResult createUser(CreateAdminUserCommand command,
                               @RequestBody @Validated(Create.class) AdminUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/admin-user/update")
  @Secured(actions = UPDATE)
  public HttpResult updateUser(UpdateAdminUserCommand command,
                               @RequestBody @Validated(Update.class) AdminUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/admin-user/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteUser(DeleteUserCommand command,
                               @RequestBody @Validated IdPayload payload) {

    return commandGateway.send(command.setUserType(UserTypes.ADMIN).setPayload(payload));
  }

  @PostMapping("/admin-user/change-password")
  @Secured(actions = UPDATE)
  public HttpResult changeUserPassword(ChangeUserPasswordCommand command,
                                       @RequestBody @Validated ChangeUserPasswordPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @GetMapping("/admin-user/choose/role")
  @Secured(actions = QUERY)
  public HttpResult chooseAdminRoles(CandidateRoleCommand command, String userId) {

    return commandGateway.send(command
      .setUserId(userId)
      .setRoleType(RoleTypes.FUNC)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @PostMapping("/admin-user/role/page")
  @Secured(actions = QUERY)
  public HttpResult selectRoleUserPage(QueryRoleUserCommand command,
                                       String userId,
                                       @RequestBody @Validated RoleUserQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command
      .setUserId(userId)
      .setQuery(query)
      .setPageClause(pageClause)
      .setOutput(OutputTypes.ROLE)
    );
  }

  @PostMapping("/admin-user/role/create")
  @Secured(actions = CREATE)
  public HttpResult batchCreateRoleUser(CreateRoleUserCommand command,
                                        @RequestBody @Validated List<RoleUserPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/admin-user/role/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRoleUser(DeleteRoleUserCommand command,
                                   @RequestBody @Validated RoleUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @GetMapping("/admin-user/privilege/auth")
  @Secured(actions = QUERY)
  public HttpResult selectUserAuthPrivileges(QueryPrivilegeCommand command, String userId) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.User)
      .setAppId(UaspConstants.UASP_APP_ID)
      .setLinkId(userId)
      .setAuthScope(true)
    );
  }

  @PostMapping("/admin-user/privilege/save")
  @Secured(actions = AUTH)
  public HttpResult saveUserPrivilege(SavePrivilegeCommand command,
                                      @RequestBody @Validated UserPrivilegePayload payload) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.User)
      .setAppId(UaspConstants.UASP_APP_ID)
      .setLinkId(payload.getUserId())
      .setPrivileges(payload.getPrivileges())
    );
  }

}
