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
