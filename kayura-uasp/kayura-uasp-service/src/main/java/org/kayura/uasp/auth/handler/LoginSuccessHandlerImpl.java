/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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

package org.kayura.uasp.auth.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.security.LoginUser;
import org.kayura.security.PermissionList;
import org.kayura.security.core.LoginSuccessHandler;
import org.kayura.security.core.LoginUserImpl;
import org.kayura.security.core.PrefixGrantedAuthority;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.entity.PrivilegeEntity;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.manage.PrivilegeManager;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class LoginSuccessHandlerImpl implements LoginSuccessHandler {

  private static final Log LOGGER = LogFactory.getLog(LoginSuccessHandlerImpl.class);

  private final EmployeeManager employeeManager;
  private final PrivilegeManager privilegeManager;
  private final RoleUserManager roleUserManager;

  public LoginSuccessHandlerImpl(EmployeeManager employeeManager,
                                 PrivilegeManager privilegeManager,
                                 RoleUserManager roleUserManager) {
    this.employeeManager = employeeManager;
    this.privilegeManager = privilegeManager;
    this.roleUserManager = roleUserManager;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {

    LoginUserImpl userImpl = (LoginUserImpl) loginUser;

    // 如果有租户ID，就获取员工信息
    if (StringUtils.hasText(userImpl.getTenantId())) {
      EmployeeEntity employee = employeeManager.selectById(userImpl.getUserId());
      if (employee != null) {
        userImpl.setCompanyId(employee.getCompanyId());
        userImpl.setCompanyName(employee.getCompanyName());
        userImpl.setDepartId(employee.getDepartId());
        userImpl.setDepartName(employee.getDepartName());
        userImpl.setRealName(employee.getRealName());
      } else {
        LOGGER.warn("用户[" + userImpl.getUsername() + "]有所属租户，却没有所属公司。");
      }
    }

    // ROOT 管理员不需要
    if (!UserTypes.ROOT.equals(loginUser.getUserType())) {
      // 拥有的角色列表
      List<String> roles = roleUserManager.selectRoleCodesByFunc(loginUser.getAppId(), loginUser.getUserId());
      if (!roles.isEmpty()) {
        HashSet<PrefixGrantedAuthority> authorities = userImpl.getAuthorities();
        for (String role : roles) {
          authorities.add(PrefixGrantedAuthority.roleOf(role));
        }
      }
      // 拥有的模块权限
      Map<String, Set<String>> codeActions =
        this.haveAuthModuleForCodeActions(loginUser.getAppId(), loginUser.getUserId());
      userImpl.setPermissions(PermissionList.of(codeActions));
    }

  }

  public List<PrivilegeEntity> selectUserHavePrivilege(String appId, String userId) {

    // 拥有的权限项列表
    List<PrivilegeEntity> privileges = new ArrayList<>();

    // 角色权限
    List<String> roleIds = roleUserManager.selectList(w -> {
      w.select(RoleUserEntity::getRoleId);
      w.eq(RoleUserEntity::getRoleType, RoleTypes.FUNC);
      w.eq(RoleUserEntity::getUserId, userId);
    }).stream().map(RoleUserEntity::getRoleId).toList();
    if (!roleIds.isEmpty()) {
      List<PrivilegeEntity> list = privilegeManager.selectList(w -> {
        w.select(PrivilegeEntity::getModuleId);
        w.select(PrivilegeEntity::getModuleCode);
        w.select(PrivilegeEntity::getActions);
        w.eq(PrivilegeEntity::getType, PrivilegeTypes.Role);
        w.in(PrivilegeEntity::getLinkId, roleIds);
        w.eq(PrivilegeEntity::getAppId, appId);
      });
      if (!list.isEmpty()) {
        privileges.addAll(list);
      }
    }

    // 个人权限
    List<PrivilegeEntity> list = privilegeManager.selectList(w -> {
      w.select(PrivilegeEntity::getModuleId);
      w.select(PrivilegeEntity::getModuleCode);
      w.select(PrivilegeEntity::getActions);
      w.eq(PrivilegeEntity::getType, PrivilegeTypes.User);
      w.eq(PrivilegeEntity::getLinkId, userId);
      w.eq(PrivilegeEntity::getAppId, appId);
    });
    if (!list.isEmpty()) {
      privileges.addAll(list);
    }

    return privileges;
  }

  /**
   * 获取指定用户下某应用下拥有的全部权限。
   *
   * @param appId  指定的某应用ID。
   * @param userId 要查询的用户ID。
   * @return 返回拥有权限的模块code与动作列表actions。
   */
  public Map<String, Set<String>> haveAuthModuleForCodeActions(String appId, String userId) {

    // 表示用户拥有指定应用下的全部权限
    Map<String, Set<String>> haveAuth = new HashMap<>();

    // 拥有的权限
    List<PrivilegeEntity> privileges = this.selectUserHavePrivilege(appId, userId);
    for (PrivilegeEntity pe : privileges) {
      if (haveAuth.containsKey(pe.getModuleCode())) {
        haveAuth.get(pe.getModuleCode()).addAll(pe.getActions());
      } else {
        haveAuth.put(pe.getModuleCode(), new HashSet<>(pe.getActions()));
      }
    }

    return haveAuth;
  }
}
