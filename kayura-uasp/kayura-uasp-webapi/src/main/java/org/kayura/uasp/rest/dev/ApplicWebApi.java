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
package org.kayura.uasp.rest.dev;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.ValidList;
import org.kayura.uasp.applic.ApplicPayload;
import org.kayura.uasp.applic.ApplicQuery;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.company.CompanyApplicPayload;
import org.kayura.uasp.company.CompanyApplicQuery;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.dev.cmd.*;
import org.kayura.uasp.organize.cmd.*;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_APPLIC)
public class ApplicWebApi {

  private final CommandGateway commandGateway;

  public ApplicWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/applic/page")
  @Secured(actions = QUERY)
  public HttpResult queryApplicPage(QueryApplicCommand command,
                                    @RequestBody ApplicQuery query,
                                    PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setNotUasp(true)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/applic/get")
  @Secured(actions = QUERY)
  public HttpResult findApplicById(GetApplicCommand command,
                                   @RequestParam("id") String appId) {

    return commandGateway.send(command.setAppId(appId));
  }

  @PostMapping("/applic/create")
  @Secured(actions = CREATE)
  public HttpResult createApplic(CreateApplicCommand command,
                                 @RequestBody @Validated(Create.class) ApplicPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/applic/update")
  @Secured(actions = UPDATE)
  public HttpResult updateApplic(UpdateApplicCommand command,
                                 @RequestBody @Validated(Update.class) ApplicPayload payload) {

    return commandGateway.send(command.setAppId(payload.getAppId()).setPayload(payload));
  }

  @PostMapping("/applic/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteApplic(DeleteApplicCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  // ------------------- ApplicCompany ------------------------

  @GetMapping("/applic/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenants(ChooseTenantCommand command,
                                  String appId) {

    return commandGateway.send(command
      .setAppId(appId)
      .setOutType(OutputTypes.SELECT)
      .setFormApp(true)
    );
  }

  @GetMapping("/applic/choose/company")
  @Secured(actions = QUERY)
  public HttpResult chooseCompanies(ChooseCompanyCommand command,
                                    CompanyTypes type, String tenantId) {

    return commandGateway.send(command
      .setTenantId(tenantId)
      .setType(type)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @PostMapping("/applic/company/page")
  @Secured(actions = QUERY)
  public HttpResult queryApplicCompanyPage(QueryCompanyApplicCommand command,
                                           String appId,
                                           @RequestBody CompanyApplicQuery query,
                                           PageClause pageClause) {

    return commandGateway.send(command
      .setAppId(appId)
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @PostMapping("/applic/company/create")
  @Secured(actions = CREATE)
  public HttpResult createApplicCompany(CreateCompanyApplicCommand command,
                                        @RequestBody @Validated(Create.class) ValidList<CompanyApplicPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/applic/company/update")
  @Secured(actions = UPDATE)
  public HttpResult updateApplicCompany(UpdateCompanyApplicCommand command,
                                        @RequestBody @Validated(Update.class) CompanyApplicPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/applic/company/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteApplicCompany(DeleteCompanyApplicCommand command,
                                        @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
