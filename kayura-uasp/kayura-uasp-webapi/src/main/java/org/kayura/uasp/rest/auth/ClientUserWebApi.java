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
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.privilege.UserPrivilegePayload;
import org.kayura.uasp.user.ClientUserPayload;
import org.kayura.uasp.user.ClientUserQuery;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_CLIENT_USER)
public class ClientUserWebApi {

  private final CommandGateway commandGateway;

  public ClientUserWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/client-user/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplics(ChooseApplicCommand command) {

    return commandGateway.send(command.setOutput(OutputTypes.SELECT));
  }

  @PostMapping("/client-user/page")
  @Secured(actions = QUERY)
  public HttpResult queryClientUserPage(QueryClientUserCommand command,
                                        @RequestBody @Validated ClientUserQuery query,
                                        PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/client-user/get")
  @Secured(actions = QUERY)
  public HttpResult findClientUserById(GetClientUserCommand command,
                                       @RequestParam("id") String userId) {

    return commandGateway.send(command.setUserId(userId));
  }

  @PostMapping("/client-user/create")
  @Secured(actions = CREATE)
  public HttpResult createClientUser(CreateClientUserCommand command,
                                     @RequestBody @Validated(Create.class) ClientUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/client-user/update")
  @Secured(actions = UPDATE)
  public HttpResult updateClientUser(UpdateClientUserCommand command,
                                     @RequestBody @Validated(Update.class) ClientUserPayload payload) {

    return commandGateway.send(command
      .setClientId(payload.getClientId())
      .setPayload(payload)
    );
  }

  @PostMapping("/client-user/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteClientUser(DeleteUserCommand command,
                                     @RequestBody IdPayload payload) {

    return commandGateway.send(command.setUserType(UserTypes.CLIENT).setPayload(payload));
  }

  // ---- privilege ----

  @GetMapping("/client-user/privilege/auth")
  @Secured(actions = QUERY)
  public HttpResult selectAuthPrivileges(QueryPrivilegeCommand command,
                                         String appId, String clientId) {

    return commandGateway.send(command
      .setAppId(appId)
      .setType(PrivilegeTypes.User)
      .setLinkId(clientId)
      .setAuthScope(true)
    );
  }

  @PostMapping("/client-user/privilege/save")
  @Secured(actions = AUTH)
  public HttpResult saveUserPrivilege(SavePrivilegeCommand command,
                                      @RequestBody UserPrivilegePayload payload) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.User)
      .setAppId(payload.getAppId())
      .setLinkId(payload.getUserId())
      .setPrivileges(payload.getPrivileges())
    );
  }

}
