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

package org.kayura.uasp.rest.organize;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.ChooseCompanyCommand;
import org.kayura.uasp.organize.cmd.QueryEmployeeCommand;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_NO_SECRET)
public class NoSecretWebApi {

  private final CommandGateway commandGateway;

  public NoSecretWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/no-secret/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command
      .setHasApp(true)
      .setIncludeApplic(true)
      .setOutType(OutputTypes.SELECT)
    );
  }

  @GetMapping("/no-secret/choose/contract-company")
  @Secured(actions = QUERY)
  public HttpResult chooseCompany(ChooseCompanyCommand command,
                                  String tenantId) {
    if (StringUtils.isBlank(tenantId)) {
      return HttpResult.error("必需指明租户ID。");
    }
    return commandGateway.send(command
      .setTenantId(tenantId)
      .setType(CompanyTypes.Contact)
      .setOutput(OutputTypes.TREE)
    );
  }

  @PostMapping("/no-secret/employee/page")
  @Secured(actions = QUERY)
  public HttpResult selectEmployeePage(QueryEmployeeCommand command,
                                       @RequestBody EmployeeQuery query,
                                       PageClause pageClause) {
    if (StringUtils.isBlank(query.getOrgId())) {
      return HttpResult.error("必需指明公司ID（orgId）。");
    }
    return commandGateway.send(command
      .setQuery(query.setOrgType(OrganizeTypes.Company))
      .setPageClause(pageClause)
    );
  }

}
