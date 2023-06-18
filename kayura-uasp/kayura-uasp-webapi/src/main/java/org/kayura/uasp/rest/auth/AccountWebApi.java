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
import org.kayura.type.HttpResult;
import org.kayura.uasp.account.AccountPayload;
import org.kayura.uasp.account.ChangePwdPayload;
import org.kayura.uasp.auth.cmd.AccountGetCommand;
import org.kayura.uasp.auth.cmd.AccountUpdateCommand;
import org.kayura.uasp.auth.cmd.ChangeOwnPasswordCommand;
import org.springframework.validation.annotation.Validated;
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
                                   @RequestBody @Validated ChangePwdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
