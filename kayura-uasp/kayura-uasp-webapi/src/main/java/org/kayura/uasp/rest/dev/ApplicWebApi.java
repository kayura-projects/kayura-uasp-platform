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
      .setHasApp(true)
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
