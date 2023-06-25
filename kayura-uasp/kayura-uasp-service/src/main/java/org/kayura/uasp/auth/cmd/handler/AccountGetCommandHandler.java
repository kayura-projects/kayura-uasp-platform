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
import org.kayura.security.Permission;
import org.kayura.security.PermissionList;
import org.kayura.security.core.LoginUserImpl;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.account.LoginAccountVo;
import org.kayura.uasp.account.MenuVo;
import org.kayura.uasp.account.ProfileVo;
import org.kayura.uasp.auth.cmd.AccountGetCommand;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleManager;
import org.kayura.uasp.func.ModuleTypes;
import org.kayura.uasp.privilege.AuthConst;
import org.kayura.uasp.utils.UaspConsts;
import org.kayura.utils.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountGetCommandHandler implements CommandHandler<AccountGetCommand, HttpResult> {

  private final ModuleManager moduleManager;

  public AccountGetCommandHandler(ModuleManager moduleManager) {
    this.moduleManager = moduleManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(AccountGetCommand command) {

    LoginUserImpl loginUser = (LoginUserImpl) command.getLoginUser();

    // 用户信息
    ProfileVo profile = ProfileVo.create()
      .setUserId(loginUser.getUserId())
      .setDisplayName(loginUser.getDisplayName())
      .setUserType(loginUser.getUserType())
      .setMobile(loginUser.getMobile())
      .setTenantId(loginUser.getTenantId())
      .setTenantName(loginUser.getTenantName())
      .setCompanyId(loginUser.getCompanyId())
      .setCompanyName(loginUser.getCompanyName())
      .setDepartName(loginUser.getDepartName())
      .setDepartName(loginUser.getDepartName())
      .setDisplayName(loginUser.getRealName());

    // 构建用户信息
    LoginAccountVo account = LoginAccountVo.create();
    account.setProfile(profile);

    List<MenuVo> mainMenus;
    if (UserTypes.ROOT.equals(loginUser.getUserType())) {

      // ROOT 拥有全部UASP功能菜单
      mainMenus = this.makeAllMenusByRoot();
    } else {

      // 生成角色列表
      Set<String> authorities = loginUser.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toSet());
      account.setAuthorities(authorities);

      // 生成权限列表
      PermissionList permissions = loginUser.getPermissions();
      account.setPermissions(permissions.toMap());

      // 生成菜单
      List<String> menuModules = permissions.stream()
        .filter(x -> x.getActions().contains(AuthConst.ACTION_MENU))
        .map(Permission::getResource)
        .toList();
      mainMenus = this.makeHaveAuthUserMenus(loginUser.getAppId(), menuModules);
    }
    account.setMainMenus(mainMenus);

    return HttpResult.okBody(account);
  }

  protected List<MenuVo> makeAllMenusByRoot() {

    // 拥有的菜单模块ID集
    List<MenuVo> result = new ArrayList<>();
    List<ModuleDefineEntity> appModules = moduleManager.chooseAllModule(UaspConsts.UASP_APP_ID);
    if (appModules.isEmpty()) {
      appModules.addAll(this.initDeveloperMenus());
    }
    List<ModuleDefineEntity> rootModules = appModules.stream()
      .filter(x -> StringUtils.isBlank(x.getParentId())).collect(Collectors.toList());
    this.makeChildrenMenus(result, rootModules, appModules);
    return result;
  }

  protected List<ModuleDefineEntity> initDeveloperMenus() {
    return List.of(
      buildModule("DEV0001", null, ModuleTypes.Category, null, "后台管理", null),
      buildModule("DEV0002", "DEV0001", ModuleTypes.Category, null, "开发管理", null),
      buildModule("DEV0003", "DEV0002", ModuleTypes.Module, "UASP_APPLIC", "应用管理", "/uasp/applic"),
      buildModule("DEV0004", "DEV0002", ModuleTypes.Module, "UASP_MODULE", "模块管理", "/uasp/module")
    );
  }

  private ModuleDefineEntity buildModule(String id, String parentId, ModuleTypes type, String code, String name, String url) {

    return ModuleDefineEntity.create()
      .setModuleId(id).setParentId(parentId).setType(type).setCode(code).setName(name).setUrl(url);
  }

  protected List<MenuVo> makeHaveAuthUserMenus(String appId, List<String> moduleCodes) {

    // 拥有的菜单模块ID集
    List<MenuVo> result = new ArrayList<>();
    if (!moduleCodes.isEmpty()) {
      List<ModuleDefineEntity> appModules = moduleManager.chooseModulesByCode(appId, moduleCodes);
      List<ModuleDefineEntity> rootModules = appModules.stream()
        .filter(x -> StringUtils.hasText(x.getParentId())).collect(Collectors.toList());
      this.makeChildrenMenus(result, rootModules, appModules);
    }
    return result;
  }

  protected void makeChildrenMenus(List<MenuVo> result, List<ModuleDefineEntity> modules, List<ModuleDefineEntity> allModules) {

    for (ModuleDefineEntity rm : modules) {

      MenuVo mb = MenuVo.create();
      if (ModuleTypes.Divide.equals(rm.getType())) {
        mb.setHeader(rm.getName());
      } else {
        mb.setLabel(rm.getName()).setIcon(rm.getIcon());
        if (ModuleTypes.Module.equals(rm.getType())) {
          mb.setCode(rm.getCode());
          mb.setTarget(rm.getTarget());
          if (StringUtils.hasText(rm.getUrl())) {
            if (!rm.getUrl().startsWith("http")) {
              mb.setRouterLink(rm.getUrl());
            } else {
              mb.setUrl(rm.getUrl());
            }
          }
        }
      }

      List<ModuleDefineEntity> childModules = allModules.stream()
        .filter(x -> rm.getModuleId().equals(x.getParentId()))
        .collect(Collectors.toList());
      if (!childModules.isEmpty()) {
        List<MenuVo> children = new ArrayList<>();
        this.makeChildrenMenus(children, childModules, allModules);
        if (!children.isEmpty()) {
          mb.setItems(children);
        }
      }

      result.add(mb);
    }
  }
}
