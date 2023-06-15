/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
