/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.rest.auth;

import org.kayura.cmd.CommandGateway;
import org.kayura.type.HttpResult;
import org.kayura.uasp.account.AccountPayload;
import org.kayura.uasp.account.ChangePwdPayload;
import org.kayura.uasp.auth.cmd.AccountGetCommand;
import org.kayura.uasp.auth.cmd.AccountUpdateCommand;
import org.kayura.uasp.auth.cmd.ChangeOwnPasswordCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class AccountWebApi {

  private final CommandGateway commandGateway;

  public AccountWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/account/get")
  public HttpResult selectAccountInfo(AccountGetCommand command) {

    return commandGateway.send(command);
  }

  @PostMapping("/account/update")
  public HttpResult updateAccount(AccountUpdateCommand command,
                                  @RequestBody AccountPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/account/change-pwd")
  public HttpResult changePassword(ChangeOwnPasswordCommand command,
                                   @RequestBody ChangePwdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
