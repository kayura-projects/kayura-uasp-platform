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

package org.kayura.uasp.dev.manage;

import org.kayura.type.UsableStatus;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.func.ModuleTypes;
import org.kayura.uasp.privilege.PrivilegeActionVo;
import org.kayura.uasp.privilege.PrivilegeModuleVo;
import org.kayura.uasp.privilege.RolePrivilege;
import org.kayura.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ModuleManager {

  private final ModuleDefineManager moduleDefineManager;
  private final ModuleActionManager moduleActionManager;

  public ModuleManager(ModuleDefineManager moduleDefineManager, ModuleActionManager moduleActionManager) {
    this.moduleDefineManager = moduleDefineManager;
    this.moduleActionManager = moduleActionManager;
  }

  /**
   * 选择某应用下可用的模块与分类。
   *
   * @param appId     指定的应用ID
   * @param moduleIds 限制范围内可用的模块。
   * @return the list
   */
  public List<ModuleDefineEntity> chooseModuleAndCategory(String appId, Set<String> moduleIds) {

    List<ModuleDefineEntity> appModules = moduleDefineManager.selectList(w -> {
      w.eq(ModuleDefineEntity::getAppId, appId);
      w.eq(ModuleDefineEntity::getStatus, UsableStatus.Valid);
      if (CollectionUtils.isNotEmpty(moduleIds)) {
        w.and(x -> x.notEq(ModuleDefineEntity::getType, ModuleTypes.Module)
          .or(y -> y.eq(ModuleDefineEntity::getType, ModuleTypes.Module).and(z -> z.in(ModuleDefineEntity::getModuleId, moduleIds))));
      }
    });

    if (!appModules.isEmpty()) {

      List<String> appModuleIds = appModules.stream()
        .filter(x -> ModuleTypes.Module.equals(x.getType()))
        .map(ModuleDefineEntity::getModuleId)
        .collect(Collectors.toList());
      List<ModuleActionEntity> appModuleActions = moduleActionManager.selectList(w -> {
        w.in(ModuleActionEntity::getModuleId, appModuleIds);
      });

      for (ModuleDefineEntity md : appModules) {
        if (ModuleTypes.Module.equals(md.getType())) {
          md.setActions(
            appModuleActions.stream()
              .filter(x -> x.getModuleId().equalsIgnoreCase(md.getModuleId()))
              .collect(Collectors.toList())
          );
        }
      }
    }
    return appModules;
  }

  public List<ModuleDefineEntity> chooseModulesByCode(String appId, List<String> moduleCodes) {

    List<ModuleDefineEntity> appModules = moduleDefineManager.selectList(w -> {
      w.eq(ModuleDefineEntity::getAppId, appId);
      w.eq(ModuleDefineEntity::getStatus, UsableStatus.Valid);
      w.and(x -> x.notEq(ModuleDefineEntity::getType, ModuleTypes.Module)
        .or(y -> y.eq(ModuleDefineEntity::getType, ModuleTypes.Module).and(z -> z.in(ModuleDefineEntity::getCode, moduleCodes))));
    });
    return appModules;
  }

  public List<ModuleDefineEntity> findScopeModules(String appId, Map<String, Set<String>> scopeAuth) {

    List<ModuleDefineEntity> appModules = chooseModuleAndCategory(appId, scopeAuth.keySet());
    List<ModuleDefineEntity> scopeModules = appModules.stream()
      .filter(x -> !ModuleTypes.Module.equals(x.getType()) || (ModuleTypes.Module.equals(x.getType()) && scopeAuth.containsKey(x.getModuleId())))
      .peek(m -> {
        if (ModuleTypes.Module.equals(m.getType())) {
          if (scopeAuth.containsKey(m.getModuleId())) {
            List<ModuleActionEntity> moduleActions = new ArrayList<>(m.getActions());
            Set<String> scopeActions = scopeAuth.get(m.getModuleId());
            for (ModuleActionEntity ma : m.getActions()) {
              if (!scopeActions.contains(ma.getCode())) {
                moduleActions.removeIf(x -> x.getCode().equals(ma.getCode()));
              }
            }
            m.setActions(moduleActions);
          }
        }
      }).collect(Collectors.toList());
    return scopeModules;
  }

  public void makeChildrenModules(List<PrivilegeModuleVo> result, List<ModuleDefineEntity> modules,
                                  List<ModuleDefineEntity> allModules, Map<String, Set<String>> haveAuth,
                                  List<RolePrivilege> rolePrivilege) {

    for (ModuleDefineEntity rm : modules) {

      PrivilegeModuleVo vo = PrivilegeModuleVo.create()
        .setModuleId(rm.getModuleId())
        .setModuleName(rm.getName())
        .setType(rm.getType())
        .setIcon(rm.getIcon());
      if (ModuleTypes.Module.equals(rm.getType())) {

        // 权限列表有这个模块，才添加
        List<ModuleActionEntity> actions = rm.getActions();
        for (ModuleActionEntity ma : actions) {

          PrivilegeActionVo av = PrivilegeActionVo.create()
            .setCode(ma.getCode())
            .setName(ma.getName())
            .setType(ma.getType());
          if (haveAuth.containsKey(rm.getModuleId())) {
            av.setChecked(haveAuth.get(rm.getModuleId()).contains(ma.getCode()));
          }
          vo.addAction(av);

          // 角色有授权就记录权限来源
          if (rolePrivilege != null) {
            rolePrivilege.stream()
              .filter(x -> x.getAuth().stream().anyMatch(y -> y.getModuleId().equals(rm.getModuleId()) && y.getActions().contains(av.getCode())))
              .forEach(m -> av.addSource(m.getRoleCode(), m.getRoleName()));
          }
        }
        result.add(vo);
      } else {

        List<ModuleDefineEntity> childModules = allModules.stream()
          .filter(x -> rm.getModuleId().equals(x.getParentId()))
          .collect(Collectors.toList());

        List<PrivilegeModuleVo> children = new ArrayList<>();
        makeChildrenModules(children, childModules, allModules, haveAuth, rolePrivilege);
        if (!children.isEmpty()) {
          vo.setChildren(children);
          result.add(vo);
        }
      }
    }
  }

  @SafeVarargs
  public final Map<String, Set<String>> mergeAuth(Map<String, Set<String>>... auths) {

    Map<String, Set<String>> result = new HashMap<>();
    for (Map<String, Set<String>> auth : auths) {
      for (Map.Entry<String, Set<String>> a : auth.entrySet()) {
        if (result.containsKey(a.getKey())) {
          Set<String> actions = auth.get(a.getKey());
          actions.addAll(a.getValue());
        } else {
          Set<String> actions = new HashSet<>(a.getValue());
          result.put(a.getKey(), actions);
        }
      }
    }
    return result;
  }

  public List<ModuleDefineEntity> chooseAllModule(String appId) {

    List<ModuleDefineEntity> appModules = moduleDefineManager.selectList(w -> {
      w.eq(ModuleDefineEntity::getAppId, appId);
      w.eq(ModuleDefineEntity::getStatus, UsableStatus.Valid);
    });
    return appModules;
  }
}
