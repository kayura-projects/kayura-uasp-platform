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
import org.kayura.type.PageClause;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.auth.cmd.BuildSneakCommand;
import org.kayura.uasp.auth.cmd.ChooseTenantCommand;
import org.kayura.uasp.auth.model.SneakPayload;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.EmployeeVo;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.ChooseCompanyCommand;
import org.kayura.uasp.organize.cmd.QueryEmployeeCommand;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_NO_SECRET)
public class NoSecretWebApi {

  private final CommandGateway commandGateway;

  public NoSecretWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/no-secret/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command
      .setFormApp(true)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @GetMapping("/no-secret/choose/contract-company")
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

  @PostMapping("/no-secret/employee/page")
  @Secured(actions = QUERY)
  public HttpResult selectEmployeePage(QueryEmployeeCommand command,
                                       @RequestBody EmployeeQuery query,
                                       PageClause pageClause) {
    if (StringUtils.isBlank(query.getOrgId())) {
      return HttpResult.error("必需指明公司ID（orgId）。");
    }
    return commandGateway.send(command
      .setQuery(query.setOrgType(OrganizeTypes.Company))
      .setIncludeApplic(true)
      .setPageClause(pageClause)
      .setEmployeeConverter(m -> EmployeeVo.create()
        .setEmployeeId(m.getEmployeeId())
        .setCompanyId(m.getCompanyId())
        .setUserName(m.getUserName())
        .setDisplayName(m.getDisplayName())
        .setMobile(m.getMobile())
        .setUserType(m.getUserType())
      ).setApplicConverter(m -> ApplicVo.create()
        .setAppId(m.getAppId())
        .setName(m.getName())
        .setUrl(m.getUrl())
      )
    );
  }

  @PostMapping("/no-secret/sneak")
  @Secured(actions = QUERY)
  public HttpResult buildSneak(BuildSneakCommand command,
                               @RequestBody @Validated SneakPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }
}
