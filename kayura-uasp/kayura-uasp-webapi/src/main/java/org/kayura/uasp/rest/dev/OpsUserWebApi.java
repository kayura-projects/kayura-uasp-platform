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
package org.kayura.uasp.rest.dev;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.UserTypes;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.dev.cmd.*;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.privilege.UserPrivilegePayload;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.role.RoleUserPayload;
import org.kayura.uasp.role.RoleUserQuery;
import org.kayura.uasp.user.OpsUserPayload;
import org.kayura.uasp.user.OpsUserQuery;
import org.kayura.uasp.user.ChangeUserPasswordPayload;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConsts;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_OPS_USER)
public class OpsUserWebApi {

  private final CommandGateway commandGateway;

  public OpsUserWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/ops-user/page")
  @Secured(actions = QUERY)
  public HttpResult queryAdminUserPage(QueryOpsUserCommand command,
                                       @RequestBody @Validated OpsUserQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/ops-user/get")
  @Secured(actions = QUERY)
  public HttpResult findUserById(GetOpsUserCommand command,
                                 String id) {

    return commandGateway.send(command.setUserId(id));
  }

  @PostMapping("/ops-user/create")
  @Secured(actions = CREATE)
  public HttpResult createUser(CreateOpsUserCommand command,
                               @RequestBody @Validated(Create.class) OpsUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/ops-user/update")
  @Secured(actions = UPDATE)
  public HttpResult updateUser(UpdateOpsUserCommand command,
                               @RequestBody @Validated(Update.class) OpsUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/ops-user/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteUser(DeleteUserCommand command,
                               @RequestBody @Validated IdPayload payload) {

    return commandGateway.send(command.setUserType(UserTypes.ADMIN).setPayload(payload));
  }

  @PostMapping("/ops-user/change-password")
  @Secured(actions = UPDATE)
  public HttpResult changeUserPassword(ChangeUserPasswordCommand command,
                                       @RequestBody @Validated ChangeUserPasswordPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @GetMapping("/ops-user/choose/role")
  @Secured(actions = QUERY)
  public HttpResult chooseAdminRoles(CandidateRoleCommand command, String userId) {

    return commandGateway.send(command
      .setUserId(userId)
      .setRoleType(RoleTypes.FUNC)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @PostMapping("/ops-user/role/page")
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

  @PostMapping("/ops-user/role/create")
  @Secured(actions = CREATE)
  public HttpResult batchCreateRoleUser(CreateRoleUserCommand command,
                                        @RequestBody @Validated List<RoleUserPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/ops-user/role/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRoleUser(DeleteRoleUserCommand command,
                                   @RequestBody @Validated RoleUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @GetMapping("/ops-user/privilege/auth")
  @Secured(actions = QUERY)
  public HttpResult selectUserAuthPrivileges(QueryPrivilegeCommand command, String userId) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.User)
      .setAppId(UaspConsts.UASP_APP_ID)
      .setLinkId(userId)
      .setAuthScope(true)
    );
  }

  @PostMapping("/ops-user/privilege/save")
  @Secured(actions = AUTH)
  public HttpResult saveUserPrivilege(SavePrivilegeCommand command,
                                      @RequestBody @Validated UserPrivilegePayload payload) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.User)
      .setAppId(UaspConsts.UASP_APP_ID)
      .setLinkId(payload.getUserId())
      .setPrivileges(payload.getPrivileges())
    );
  }

}
