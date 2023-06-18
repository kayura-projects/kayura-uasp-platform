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
