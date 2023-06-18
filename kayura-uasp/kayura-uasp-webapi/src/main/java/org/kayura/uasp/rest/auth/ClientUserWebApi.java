/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
