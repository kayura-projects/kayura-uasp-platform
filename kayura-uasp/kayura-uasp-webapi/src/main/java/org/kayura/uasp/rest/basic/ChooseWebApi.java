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
package org.kayura.uasp.rest.basic;

import org.kayura.cmd.CommandGateway;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.cmd.ChooseEmployeeCommand;
import org.kayura.uasp.organize.cmd.SelectOrganizeTreeCommand;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class ChooseWebApi {

  private final CommandGateway commandGateway;

  public ChooseWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/choose/org/tree")
  public HttpResult chooseOrganizeTree(SelectOrganizeTreeCommand command,
                                       String tenantId, Integer level) {

    return commandGateway.send(command
      .setCompanyId(tenantId)
      .setLevel(level)
      .setStatus(DataStatus.Valid)
    );
  }

  @PostMapping("/choose/user/query")
  public HttpResult queryUsers(ChooseEmployeeCommand command,
                               @RequestBody @Validated EmployeeQuery query,
                               OrderByClause orderByClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setOrderByClause(orderByClause)
    );
  }

}
