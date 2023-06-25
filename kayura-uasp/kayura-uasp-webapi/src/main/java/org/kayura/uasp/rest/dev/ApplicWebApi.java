/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
      .setOutput(OutputTypes.SELECT)
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
