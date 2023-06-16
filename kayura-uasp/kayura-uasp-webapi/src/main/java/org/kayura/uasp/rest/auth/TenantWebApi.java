/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
