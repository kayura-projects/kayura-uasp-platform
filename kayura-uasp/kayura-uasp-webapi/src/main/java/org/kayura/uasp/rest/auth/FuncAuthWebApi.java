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

    return commandGateway.send(command.setFormApp(true).setOutput(OutputTypes.SELECT));
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
