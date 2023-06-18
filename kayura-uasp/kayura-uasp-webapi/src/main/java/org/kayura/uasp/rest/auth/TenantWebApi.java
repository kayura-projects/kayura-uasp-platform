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
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.ValidList;
import org.kayura.uasp.applic.ApplicQuery;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.company.CompanyApplicPayload;
import org.kayura.uasp.company.CompanyApplicQuery;
import org.kayura.uasp.dev.cmd.QueryApplicCommand;
import org.kayura.uasp.organize.cmd.CreateCompanyApplicCommand;
import org.kayura.uasp.organize.cmd.DeleteCompanyApplicCommand;
import org.kayura.uasp.organize.cmd.QueryCompanyApplicCommand;
import org.kayura.uasp.organize.cmd.UpdateCompanyApplicCommand;
import org.kayura.uasp.tenant.TenantPayload;
import org.kayura.uasp.tenant.TenantQuery;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

/** 租用公司WEBAPI提供类 */
@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_TENANT)
public class TenantWebApi {

  private final CommandGateway commandGateway;

  public TenantWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/tenant/page")
  @Secured(actions = QUERY)
  public HttpResult queryTenantPage(QueryTenantCommand command,
                                    @RequestBody TenantQuery query, PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/tenant/get")
  @Secured(actions = QUERY)
  public HttpResult findTenantById(GetTenantCommand command,
                                   @RequestParam("id") String tenantId) {

    return commandGateway.send(command.setTenantId(tenantId));
  }

  @PostMapping("/tenant/create")
  @Secured(actions = CREATE)
  public HttpResult createTenant(CreateTenantCommand command,
                                 @RequestBody @Validated(Create.class) TenantPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/tenant/update")
  @Secured(actions = UPDATE)
  public HttpResult updateTenant(UpdateTenantCommand command,
                                 @RequestBody @Validated(Update.class) TenantPayload payload) {

    return commandGateway.send(command
      .setTenantId(payload.getTenantId())
      .setPayload(payload)
    );
  }

  @PostMapping("/tenant/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteTenant(DeleteTenantCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  /** choose **/

  @GetMapping("/tenant/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseTenants(QueryApplicCommand command) {

    return commandGateway.send(command
      .setNotUasp(Boolean.TRUE)
      .setQuery(ApplicQuery.create().setStatus(DataStatus.Valid))
    );
  }

  /** applic **/

  @PostMapping("/tenant/applic/page")
  @Secured(actions = QUERY)
  public HttpResult queryApplicTenantPage(QueryCompanyApplicCommand command,
                                          String companyId,
                                          @RequestBody @Validated CompanyApplicQuery query,
                                          PageClause pageClause) {

    return commandGateway.send(command
      .setCompanyId(companyId)
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @PostMapping("/tenant/applic/create")
  @Secured(actions = CREATE)
  public HttpResult createApplicTenant(CreateCompanyApplicCommand command,
                                       @RequestBody @Validated(Create.class) ValidList<CompanyApplicPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/tenant/applic/update")
  @Secured(actions = UPDATE)
  public HttpResult updateApplicTenant(UpdateCompanyApplicCommand command,
                                       @RequestBody @Validated(Update.class) CompanyApplicPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/tenant/applic/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteApplicTenant(DeleteCompanyApplicCommand command,
                                       @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
