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
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.basic.cmd.ChooseDictItemCommand;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.organize.EmployeePayload;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.IdentityPayload;
import org.kayura.uasp.organize.IdentityQuery;
import org.kayura.uasp.organize.cmd.*;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.privilege.UserPrivilegePayload;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.role.RoleUserPayload;
import org.kayura.uasp.role.RoleUserQuery;
import org.kayura.uasp.user.ChangeUserPasswordPayload;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_EMPLOYEE)
public class EmployeeWebApi {

  private final CommandGateway commandGateway;

  public EmployeeWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/employee/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenant(ChooseTenantCommand command) {

    return commandGateway.send(command.setOutType(OutputTypes.SELECT));
  }

  @GetMapping("/employee/choose/org-tree")
  @Secured(actions = QUERY)
  public HttpResult selectOrganizeTree(SelectOrganizeTreeCommand command,
                                       String companyId, int level) {

    return commandGateway.send(command
      .setCompanyId(companyId)
      .setLevel(level)
      .setStatus(DataStatus.Valid)
    );
  }

  // ---- employee ----

  @PostMapping("/employee/page")
  @Secured(actions = QUERY)
  public HttpResult selectEmployeePage(QueryEmployeeCommand command,
                                       @RequestBody EmployeeQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/employee/get")
  @Secured(actions = QUERY)
  public HttpResult selectEmployeeById(GetEmployeeCommand command,
                                       @RequestParam("id") String employeeId) {

    return commandGateway.send(command.setEmployeeId(employeeId));
  }

  @GetMapping("/employee/choose/dict")
  @Secured(actions = QUERY)
  public HttpResult chooseEmployeeDict(ChooseDictItemCommand command, String tenantId) {

    return commandGateway.send(command
      .setDefines(Map.of(
        "UASP_MARITAL", "marital",
        "UASP_EDUCATION", "education",
        "UASP_ORIGIN", "origin",
        "UASP_NATION", "nation"
      )).setTenantId(tenantId)
    );
  }

  @PostMapping("/employee/create")
  @Secured(actions = CREATE)
  public HttpResult createEmployee(CreateEmployeeCommand command,
                                   @RequestBody @Validated(Create.class) EmployeePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/employee/update")
  @Secured(actions = UPDATE)
  public HttpResult updateEmployee(UpdateEmployeeCommand command,
                                   @RequestBody @Validated(Update.class) EmployeePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/employee/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteEmployee(DeleteEmployeeCommand command,
                                   @RequestBody IdPayload payload) {

    return commandGateway.send(command
      .setEmployeeId(payload.getId())
      .setPayload(payload)
    );
  }

  // ---- identity ----

  @PostMapping("/employee/identity/page")
  @Secured(actions = QUERY)
  public HttpResult selectIdentityPage(QueryIdentityCommand command,
                                       @RequestBody @Validated IdentityQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command
      .setEmployeeId(query.getEmployeeId())
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/employee/identity/get")
  @Secured(actions = QUERY)
  public HttpResult findIdentityById(GetIdentityCommand command,
                                     @RequestParam("id") String identityId) {

    return commandGateway.send(command.setIdentityId(identityId));
  }

  @PostMapping("/employee/identity/create")
  @Secured(actions = CREATE)
  public HttpResult createIdentity(CreateIdentityCommand command,
                                   @RequestBody @Validated(Create.class) IdentityPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/employee/identity/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteIdentity(DeleteIdentityCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command
      .setIdentityId(payload.getId())
      .setIdentityIds(payload.getIds())
    );
  }

  // ---- role ----

  @GetMapping("/employee/candidate/role")
  @Secured(actions = QUERY)
  public HttpResult chooseRoleByCompany(CandidateRoleCommand command,
                                        String employeeId) {

    return commandGateway.send(command
      .setUserId(employeeId)
      .setRoleType(RoleTypes.FUNC)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @PostMapping("/employee/role/page")
  @Secured(actions = QUERY)
  public HttpResult selectRoleUserPage(QueryRoleUserCommand command,
                                       String employeeId,
                                       @RequestBody RoleUserQuery query,
                                       PageClause pageClause) {

    return commandGateway.send(command
      .setUserId(employeeId)
      .setQuery(query)
      .setPageClause(pageClause)
      .setOutput(OutputTypes.ROLE)
    );
  }

  @PostMapping("/employee/role/create")
  @Secured(actions = CREATE)
  public HttpResult batchCreateRoleUser(CreateRoleUserCommand command,
                                        @RequestBody @Validated List<RoleUserPayload> payloads) {

    return commandGateway.send(command.setPayloads(payloads));
  }

  @PostMapping("/employee/role/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteRoleUser(DeleteRoleUserCommand command, @RequestBody @Validated RoleUserPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  // ---- other ----

  @PostMapping("/employee/change-password")
  @Secured(actions = UPDATE)
  public HttpResult changeEmployeePassword(ChangeUserPasswordCommand command,
                                           @RequestBody ChangeUserPasswordPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @GetMapping("/employee/choose/have-applic")
  @Secured(actions = QUERY)
  public HttpResult chooseHaveApplic(ChooseApplicCommand command,
                                     String employeeId) {

    return commandGateway.send(command
      .setUserId(employeeId)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @GetMapping("/employee/privilege/auth")
  @Secured(actions = QUERY)
  public HttpResult selectAuthPrivilege(QueryPrivilegeCommand command,
                                        String appId, String employeeId) {

    return commandGateway.send(command
      .setAppId(appId)
      .setType(PrivilegeTypes.User)
      .setLinkId(employeeId)
      .setAuthScope(true)
    );
  }

  @GetMapping("/employee/privilege/view")
  @Secured(actions = QUERY)
  public HttpResult selectViewPrivileges(QueryPrivilegeCommand command,
                                         String appId, String employeeId) {

    return commandGateway.send(command
      .setAppId(appId)
      .setType(PrivilegeTypes.User)
      .setLinkId(employeeId)
      .setAuthScope(false)
    );
  }

  @PostMapping("/employee/privilege/save")
  @Secured(actions = AUTH)
  public HttpResult saveEmployeePrivilege(SavePrivilegeCommand command,
                                          @RequestBody @Validated UserPrivilegePayload payload) {

    return commandGateway.send(command
      .setType(PrivilegeTypes.User)
      .setAppId(payload.getAppId())
      .setLinkId(payload.getUserId())
      .setPrivileges(payload.getPrivileges())
    );
  }

}
