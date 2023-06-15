/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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

    return commandGateway.send(command.setHasApp(true).setOutType(OutputTypes.SELECT));
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
      .setOutput(OutputTypes.TREE)
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
