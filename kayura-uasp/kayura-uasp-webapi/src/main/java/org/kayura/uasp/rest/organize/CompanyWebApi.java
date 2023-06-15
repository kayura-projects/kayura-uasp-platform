/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.rest.organize;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.basic.cmd.ChooseDictItemCommand;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.company.CompanyPayload;
import org.kayura.uasp.company.CompanyQuery;
import org.kayura.uasp.organize.cmd.*;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_COMPANY)
public class CompanyWebApi {

  private final CommandGateway commandGateway;

  public CompanyWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/company/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutType(OutputTypes.TREE));
  }

  @GetMapping("/company/choose/org-category")
  @Secured(actions = QUERY)
  public HttpResult chooseOrgCategory(ChooseDictItemCommand command, String tenantId) {

    return commandGateway.send(command
      .setDefine(UaspConstants.UASP_ORG_CATEGORY)
      .setTenantId(tenantId)
    );
  }

  @PostMapping("/company/page")
  @Secured(actions = QUERY)
  public HttpResult queryCompanyPage(QueryCompanyCommand command,
                                     @RequestBody @Validated CompanyQuery query,
                                     PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/company/get")
  @Secured(actions = QUERY)
  public HttpResult findCompanyById(GetCompanyCommand command,
                                    @RequestParam("id") String companyId) {

    return commandGateway.send(command.setCompanyId(companyId));
  }

  @PostMapping("/company/create")
  @Secured(actions = CREATE)
  public HttpResult createCompany(CreateCompanyCommand command,
                                  @RequestBody @Validated(Create.class) CompanyPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/company/update")
  @Secured(actions = UPDATE)
  public HttpResult updateCompany(UpdateCompanyCommand command,
                                  @RequestBody @Validated(Update.class) CompanyPayload payload) {

    return commandGateway.send(command
      .setCompanyId(payload.getCompanyId())
      .setPayload(payload)
    );
  }

  @PostMapping("/company/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteCompany(DeleteCompanyCommand command,
                                  @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
