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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.QueryPrivilegeCommand;
import org.kayura.uasp.auth.entity.*;
import org.kayura.uasp.auth.manage.*;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleManager;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.privilege.ModuleAction;
import org.kayura.uasp.privilege.PrivilegeModuleVo;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.privilege.RolePrivilege;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.utils.UaspConsts;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class QueryPrivilegeCommandHandler implements CommandHandler<QueryPrivilegeCommand, HttpResult> {

  private final RoleManager roleManager;
  private final PrivilegeManager privilegeManager;
  private final ModuleManager moduleManager;
  private final CompanyManager companyManager;
  private final UserManager userManager;
  private final EmployeeManager employeeManager;
  private final RoleUserManager roleUserManager;

  public QueryPrivilegeCommandHandler(RoleManager roleManager,
                                      PrivilegeManager privilegeManager,
                                      ModuleManager moduleManager,
                                      CompanyManager companyManager,
                                      UserManager userManager,
                                      EmployeeManager employeeManager,
                                      RoleUserManager roleUserManager) {
    this.roleManager = roleManager;
    this.privilegeManager = privilegeManager;
    this.moduleManager = moduleManager;
    this.companyManager = companyManager;
    this.userManager = userManager;
    this.employeeManager = employeeManager;
    this.roleUserManager = roleUserManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryPrivilegeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    PrivilegeTypes type = command.getType();
    String appId = command.getAppId();
    String linkId = command.getLinkId();
    boolean authScope = command.isAuthScope();

    List<PrivilegeModuleVo> privileges = null;
    if (PrivilegeTypes.Role.equals(type)) {
      privileges = queryRolePrivilege(appId, linkId, authScope);
    } else if (PrivilegeTypes.Company.equals(type)) {
      privileges = this.queryPrivilegesByCompany(appId, linkId, authScope);
    } else if (PrivilegeTypes.User.equals(type)) {
      privileges = this.queryUserPrivilege(appId, linkId, authScope);
    }

    privileges = Optional.ofNullable(privileges).orElse(new ArrayList<>());
    return HttpResult.okBody(privileges);
  }

  protected List<PrivilegeModuleVo> queryUserPrivilege(String appId, String linkId, boolean authScope) {
    List<PrivilegeModuleVo> privileges;
    UserEntity user = userManager.selectById(linkId);

    if (UserTypes.USER.equals(user.getUserType()) || UserTypes.MANAGER.equals(user.getUserType())) {
      privileges = this.queryEmployeePrivilege(appId, linkId, authScope);
    } else {
      privileges = this.queryAdminUserPrivilege(appId, linkId, authScope);
    }
    return privileges;
  }

  protected List<PrivilegeModuleVo> queryRolePrivilege(String appId, String linkId, boolean authScope) {

    List<PrivilegeModuleVo> privileges = null;
    RoleEntity role = roleManager.selectOne(w -> {
      w.select(RoleEntity::getRoleId);
      w.select(RoleEntity::getAppId);
      w.select(RoleEntity::getTenantId);
      w.eq(RoleEntity::getRoleId, linkId);
      if (StringUtils.hasText(appId)) {
        w.eq(RoleEntity::getAppId, appId);
      }
      w.eq(RoleEntity::getType, RoleTypes.FUNC);
    });
    if (role != null) {
      if (UaspConsts.UASP_APP_ID.equals(appId)) {
        privileges = this.queryPrivilegesByAdminRole(role, authScope);
      } else {
        privileges = this.queryPrivilegesByRole(role, authScope);
      }
    }
    return privileges;
  }

  protected List<PrivilegeModuleVo> queryPrivilegesByAdminRole(RoleEntity role, boolean authScope) {

    final String appId = UaspConsts.UASP_APP_ID;
    final String roleId = role.getRoleId();

    List<PrivilegeModuleVo> result = new ArrayList<>();

    if (authScope) {

      Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Role, roleId);
      List<ModuleDefineEntity> scopeModules = moduleManager.chooseModuleAndCategory(appId, null);
      List<ModuleDefineEntity> rootModules = scopeModules.stream()
        .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
      moduleManager.makeChildrenModules(result, rootModules, scopeModules, haveAuth, null);

    } else {

      Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Role, roleId);
      List<ModuleDefineEntity> appModules = moduleManager.chooseModuleAndCategory(appId, haveAuth.keySet());
      List<ModuleDefineEntity> rootModules = appModules.stream()
        .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
      moduleManager.makeChildrenModules(result, rootModules, appModules, haveAuth, null);

    }

    return result;
  }

  protected List<PrivilegeModuleVo> queryPrivilegesByRole(RoleEntity role, boolean authScope) {

    // 构建输入对象集
    List<PrivilegeModuleVo> privileges = new ArrayList<>();

    // 构建模块树
    String appId = role.getAppId();
    String roleId = role.getRoleId();

    if (authScope) {

      String tenantId = role.getTenantId();
      if (StringUtils.hasText(tenantId)) {
        Map<String, Set<String>> scopeAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Company, tenantId);
        List<ModuleDefineEntity> scopeModules = moduleManager.findScopeModules(appId, scopeAuth);
        List<ModuleDefineEntity> rootModules = scopeModules.stream()
          .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
        Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Role, roleId);
        moduleManager.makeChildrenModules(privileges, rootModules, scopeModules, haveAuth, null);
      } else {
        List<ModuleDefineEntity> scopeModules = moduleManager.chooseModuleAndCategory(appId, null);
        List<ModuleDefineEntity> rootModules = scopeModules.stream()
          .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
        Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Role, roleId);
        moduleManager.makeChildrenModules(privileges, rootModules, scopeModules, haveAuth, null);
      }

    } else {

      Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Role, roleId);
      List<ModuleDefineEntity> appModules = moduleManager.chooseModuleAndCategory(appId, haveAuth.keySet());
      List<ModuleDefineEntity> rootModules = appModules.stream()
        .filter(x -> StringUtils.hasText(x.getParentId())).toList();
      moduleManager.makeChildrenModules(privileges, rootModules, appModules, haveAuth, null);
    }

    return privileges;
  }

  protected List<PrivilegeModuleVo> queryPrivilegesByCompany(String appId, String companyId, boolean authScope) {

    CompanyEntity company = companyManager.selectById(companyId);
    String tenantId = company.getTenantId();

    // 构建输入对象集
    List<PrivilegeModuleVo> result = new ArrayList<>();

    // 租用公司获取
    if (company.getType().equals(CompanyTypes.Tenant)) {

      // 构建模块树
      Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Company, companyId);
      List<ModuleDefineEntity> scopeModules = moduleManager.chooseModuleAndCategory(appId, null);
      List<ModuleDefineEntity> rootModules = scopeModules.stream()
        .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
      moduleManager.makeChildrenModules(result, rootModules, scopeModules, haveAuth, null);

    } else {

      // 获取租用公司的限制权限
      Map<String, Set<String>> scopeAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Company, tenantId);
      List<ModuleDefineEntity> scopeModules = moduleManager.findScopeModules(appId, scopeAuth);
      List<ModuleDefineEntity> rootModules = scopeModules.stream()
        .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
      // 获取往来公司拥有的权限
      Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Company, companyId);
      moduleManager.makeChildrenModules(result, rootModules, scopeModules, haveAuth, null);

    }

    return result;
  }

  protected List<PrivilegeModuleVo> queryAdminUserPrivilege(String appId, String userId, boolean authScope) {

    // 构建输入对象集
    List<PrivilegeModuleVo> result = new ArrayList<>();

    // 构建模块树
    Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.User, userId);
    List<ModuleDefineEntity> scopeModules = moduleManager.chooseModuleAndCategory(appId, null);
    List<ModuleDefineEntity> rootModules = scopeModules.stream()
      .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
    moduleManager.makeChildrenModules(result, rootModules, scopeModules, haveAuth, null);

    return result;
  }

  protected List<PrivilegeModuleVo> queryEmployeePrivilege(String appId, String employeeId, boolean authScope) {

    // 授权范围
    EmployeeEntity employee = employeeManager.selectOne(w -> {
      w.select(EmployeeEntity::getTenantId);
      w.select(EmployeeEntity::getCompanyId);
      w.select(EmployeeEntity::getCompanyType);
      w.eq(EmployeeEntity::getEmployeeId, employeeId);
    });

    List<PrivilegeModuleVo> result = new ArrayList<>();
    if (employee != null) {

      if (authScope) {

        Map<String, Set<String>> scopeAuth;
        if (CompanyTypes.Tenant.equals(employee.getCompanyType())) {
          scopeAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Company, employee.getTenantId());
        } else {
          scopeAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.Company, employee.getCompanyId());
        }

        // 角色权限
        List<PrivilegeEntity> rolePrivileges = new ArrayList<>();
        List<RolePrivilege> roleAuths = this.fetchHaveRoleAuth(appId, employeeId, rolePrivileges);
        Map<String, Set<String>> haveAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.User, employeeId);
        List<ModuleDefineEntity> scopeModules = moduleManager.findScopeModules(appId, scopeAuth);
        List<ModuleDefineEntity> rootModules = scopeModules.stream()
          .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
        moduleManager.makeChildrenModules(result, rootModules, scopeModules, haveAuth, roleAuths);

      } else {

        // 角色权限
        List<PrivilegeEntity> rolePrivileges = new ArrayList<>();
        List<RolePrivilege> roleAuths = fetchHaveRoleAuth(appId, employeeId, rolePrivileges);
        Map<String, Set<String>> roleAuthMap = privilegeManager.mergePrivilege(rolePrivileges);
        // 个人权限
        Map<String, Set<String>> userAuth = privilegeManager.findHaveAuth(appId, PrivilegeTypes.User, employeeId);
        Map<String, Set<String>> haveAuth = moduleManager.mergeAuth(roleAuthMap, userAuth);
        // 构建模块树
        List<ModuleDefineEntity> appModules = moduleManager.chooseModuleAndCategory(appId, haveAuth.keySet());
        List<ModuleDefineEntity> rootModules = appModules.stream()
          .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
        moduleManager.makeChildrenModules(result, rootModules, appModules, haveAuth, roleAuths);

      }
    }

    return result;
  }

  protected List<RolePrivilege> fetchHaveRoleAuth(String appId, String employeeId, List<PrivilegeEntity> rolePrivileges) {

    List<RolePrivilege> roleAuths = roleUserManager.selectList(w -> {
      w.select(RoleUserEntity::getRoleId);
      w.select(RoleUserEntity::getRoleCode);
      w.select(RoleUserEntity::getRoleName);
      w.eq(RoleUserEntity::getRoleType, RoleTypes.FUNC);
      w.eq(RoleUserEntity::getUserId, employeeId);
    }).stream().map(m ->
      RolePrivilege.create().setRoleId(m.getRoleId()).setRoleCode(m.getRoleCode()).setRoleName(m.getRoleName())
    ).toList();

    List<String> roleIds = roleAuths.stream().map(RolePrivilege::getRoleId).toList();
    if (!roleIds.isEmpty()) {
      List<PrivilegeEntity> list = privilegeManager.selectList(w -> {
        w.select(PrivilegeEntity::getLinkId);
        w.select(PrivilegeEntity::getModuleId);
        w.select(PrivilegeEntity::getActions);
        w.eq(PrivilegeEntity::getType, PrivilegeTypes.Role);
        w.in(PrivilegeEntity::getLinkId, roleIds);
        w.eq(PrivilegeEntity::getAppId, appId);
      });
      for (RolePrivilege ra : roleAuths) {
        List<ModuleAction> auth = list.stream().filter(x -> x.getLinkId().equals(ra.getRoleId())).map(m ->
          ModuleAction.create().setModuleId(m.getModuleId()).setActions(m.getActions())
        ).toList();
        ra.setAuth(auth);
      }
      if (!list.isEmpty()) {
        rolePrivileges.addAll(list);
      }
    }
    return roleAuths;
  }
}
