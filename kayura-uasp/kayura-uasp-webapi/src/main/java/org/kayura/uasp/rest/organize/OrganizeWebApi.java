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
import org.kayura.uasp.organize.DepartPayload;
import org.kayura.uasp.organize.OrganizeQuery;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.PositionPayload;
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
@Secured(resource = UASP_ORGANIZE)
public class OrganizeWebApi {

  private final CommandGateway commandGateway;

  public OrganizeWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  // -------------- main tree --------------

  @GetMapping("/organize/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutType(OutputTypes.SELECT));
  }

  @GetMapping("/organize/choose/org-category")
  @Secured(actions = QUERY)
  public HttpResult chooseOrgCategory(ChooseDictItemCommand command, String tenantId) {

    return commandGateway.send(command
      .setDefine(UaspConstants.UASP_ORG_CATEGORY)
      .setTenantId(tenantId)
    );
  }

  // -------------- organize --------------

  @GetMapping("/organize/tree")
  @Secured(actions = QUERY)
  public HttpResult selectOrganizeTree(SelectOrganizeTreeCommand command, String tenantId) {

    return commandGateway.send(command.setCompanyId(tenantId));
  }

  @PostMapping("/organize/page")
  @Secured(actions = QUERY)
  public HttpResult selectOrganizePage(QueryOrganizeCommand command,
                                       @RequestBody OrganizeQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/organize/get")
  @Secured(actions = QUERY)
  public HttpResult findOrganizeById(GetOrganizeCommand command,
                                     @RequestParam("id") String orgId,
                                     OrganizeTypes type) {

    return commandGateway.send(command.setOrgType(type).setOrgId(orgId));
  }

  @PostMapping("/organize/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteOrganize(DeleteOrganizeCommand command,
                                   OrganizeTypes type,
                                   @RequestBody IdPayload payload) {

    return commandGateway.send(command.setType(type).setPayload(payload));
  }

  // ----------- company -----------

  @PostMapping("/organize/company/create")
  @Secured(actions = CREATE)
  public HttpResult createCompany(CreateCompanyCommand command,
                                  @RequestBody @Validated(Create.class) CompanyPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/organize/company/update")
  @Secured(actions = UPDATE)
  public HttpResult updateCompany(UpdateCompanyCommand command,
                                  @RequestBody @Validated(Update.class) CompanyPayload payload) {

    return commandGateway.send(command
      .setCompanyId(payload.getCompanyId())
      .setPayload(payload)
    );
  }

  // ----------- department -----------

  @PostMapping("/organize/depart/create")
  @Secured(actions = CREATE)
  public HttpResult createDepart(CreateDepartCommand command,
                                 @RequestBody @Validated(Create.class) DepartPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/organize/depart/update")
  @Secured(actions = UPDATE)
  public HttpResult updateDepart(UpdateDepartCommand command,
                                 @RequestBody @Validated(Update.class) DepartPayload payload) {

    return commandGateway.send(command
      .setDepartId(payload.getDepartId())
      .setPayload(payload)
    );
  }

  // ----------- position -----------

  @PostMapping("/organize/position/create")
  @Secured(actions = CREATE)
  public HttpResult createPosition(CreatePositionCommand command,
                                   @RequestBody @Validated(Create.class) PositionPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/organize/position/update")
  @Secured(actions = UPDATE)
  public HttpResult updatePosition(UpdatePositionCommand command,
                                   @RequestBody @Validated(Update.class) PositionPayload payload) {

    return commandGateway.send(command
      .setPositionId(payload.getPositionId())
      .setPayload(payload)
    );
  }

}
