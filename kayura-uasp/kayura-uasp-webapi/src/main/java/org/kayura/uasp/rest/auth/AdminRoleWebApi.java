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
package org.kayura.uasp.rest.auth;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.privilege.RolePrivilegePayload;
import org.kayura.uasp.role.*;
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
@Secured(resource = UASP_ADMIN_ROLE)
public class AdminRoleWebApi {

  private final CommandGateway commandGateway;

  public AdminRoleWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  // ------------ admin role --------------

  @PostMapping("/admin-role/page")
  @Secured(actions = QUERY)
  public HttpResult queryAdminRolePage(QueryRoleCommand command,
                                       @RequestBody RoleQuery query, PageClause pageClause) {

    return commandGateway.send(command
      .setAppId(UaspConsts.UASP_APP_ID)
      .setQuery(query)
      .setRoleType(RoleTypes.FUNC)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/admin-role/get")
  @Secured(actions = QUERY)
  public HttpResult findRoleById(GetRoleCommand command, String id) {

    return commandGateway.send(command.setRoleId(id));
  }

  @PostMapping("/admin-role/create")
  @Secured(actions = CREATE)
  public HttpResult createAdminRole(CreateRoleCommand command,
                                    @RequestBody @Validated(Create.class) RolePayload payload) {

    return commandGateway.send(command
      .setRoleType(RoleTypes.FUNC)
      .setPayload(payload
        .setTenantId(null)
        .setAppId(UaspConsts.UASP_APP_ID)
      ));
  }

  @PostMapping("/admin-role/update")
  @Secured(actions = UPDATE)
  public HttpResult updateAdminRole(UpdateRoleCommand command,
                                    @RequestBody @Validated(Update.class) RolePayload payload) {

    return commandGateway.send(command
      .setRoleId(payload.getRoleId())
      .setRoleType(RoleTypes.FUNC)
      .setPayload(payload)
    );
  }

  @PostMapping("/admin-role/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRole(DeleteRoleCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload).setRoleType(RoleTypes.FUNC));
  }

  // ------------ role user --------------

  @GetMapping("/admin-role/choose/user")
  @Secured(actions = QUERY)
  public HttpResult chooseAdminUsers(ChooseUserCommand command, String roleId) {

    return commandGateway.send(command.setRoleId(roleId).setUserTypes(List.of(UserTypes.ADMIN)));
  }

  @PostMapping("/admin-role/user/page")
  @Secured(actions = QUERY)
  public HttpResult selectRoleUserPage(QueryRoleUserCommand command,
                                       String roleId,
                                       @RequestBody @Validated RoleUserQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command
      .setRoleId(roleId)
      .setQuery(query)
      .setPageClause(pageClause)
      .setOutput(OutputTypes.USER)
    );
  }

  @PostMapping("/admin-role/user/create")
  @Secured(actions = CREATE)
  public HttpResult batchCreateRoleUser(CreateRoleUserCommand command,
                                        @RequestBody @Validated(Create.class) List<RoleUserPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/admin-role/user/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRoleUser(DeleteRoleUserCommand command,
                                   @RequestBody @Validated RoleUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  // ---------- privilege --------------

  @GetMapping("/admin-role/privilege/auth")
  @Secured(actions = QUERY)
  public HttpResult selectAuthPrivileges(QueryPrivilegeCommand command, String roleId) {

    return commandGateway.send(command
      .setAppId(UaspConsts.UASP_APP_ID)
      .setType(PrivilegeTypes.Role)
      .setLinkId(roleId)
      .setAuthScope(true)
    );
  }

  @GetMapping("/admin-role/privilege/view")
  @Secured(actions = QUERY)
  public HttpResult selectViewPrivileges(QueryPrivilegeCommand command, String roleId) {

    return commandGateway.send(command
      .setAppId(UaspConsts.UASP_APP_ID)
      .setType(PrivilegeTypes.Role)
      .setLinkId(roleId)
      .setAuthScope(false)
    );
  }

  @PostMapping("/admin-role/privilege/save")
  @Secured(actions = AUTH)
  public HttpResult saveEmployeePrivilege(SavePrivilegeCommand command,
                                          @RequestBody RolePrivilegePayload payload) {

    return commandGateway.send(command
      .setAppId(UaspConsts.UASP_APP_ID)
      .setType(PrivilegeTypes.Role)
      .setLinkId(payload.getRoleId())
      .setPrivileges(payload.getPrivileges())
    );
  }

}
