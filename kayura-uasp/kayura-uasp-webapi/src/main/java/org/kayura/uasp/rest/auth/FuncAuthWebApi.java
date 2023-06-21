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
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.organize.cmd.ChooseCompanyCommand;
import org.kayura.uasp.privilege.CompanyPrivilegePayload;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_FUNC_AUTH)
public class FuncAuthWebApi {

  private final CommandGateway commandGateway;

  public FuncAuthWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/func-auth/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command.setFormApp(true).setOutType(OutputTypes.SELECT));
  }

  @GetMapping("/func-auth/choose/contract-company")
  @Secured(actions = QUERY)
  public HttpResult chooseCompany(ChooseCompanyCommand command,
                                  String tenantId) {

    if (StringUtils.isBlank(tenantId)) {
      return HttpResult.error("必需指明租户ID。");
    }

    return commandGateway.send(command
      .setTenantId(tenantId)
      .setType(CompanyTypes.Contact)
      .setOutput(OutputTypes.SELECT)
    );
  }

  /** Company Privilege */

  @GetMapping("/func-auth/company/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseAppByCompany(ChooseApplicCommand command,
                                       String companyId) {

    return commandGateway.send(command
      .setCompanyId(companyId)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @GetMapping("/func-auth/company/auth")
  @Secured(actions = QUERY)
  public HttpResult selectPrivileges(QueryPrivilegeCommand command,
                                     String appId, String companyId) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.Company)
      .setAppId(appId)
      .setLinkId(companyId)
    );
  }

  @PostMapping("/func-auth/company/save")
  @Secured(actions = AUTH)
  public HttpResult savePrivileges(SavePrivilegeCommand command,
                                   @RequestBody CompanyPrivilegePayload payload) {

    return commandGateway.send(command
      .setAppId(payload.getAppId())
      .setType(PrivilegeTypes.Company)
      .setLinkId(payload.getCompanyId())
      .setPrivileges(payload.getPrivileges())
    );
  }
}
