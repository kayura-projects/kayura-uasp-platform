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

package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.auth.entity.PrivilegeEntity;
import org.kayura.uasp.auth.mapper.PrivilegeMapper;
import org.kayura.uasp.privilege.ModuleAction;
import org.kayura.uasp.privilege.PrivilegeBody;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PrivilegeManager extends CrudManagerImpl<PrivilegeMapper, PrivilegeEntity> {

  protected PrivilegeManager(PrivilegeMapper baseMapper) {
    super(baseMapper);
  }

  public void savePrivileges(PrivilegeBody form) {

    // 清除现有权限
    this.deleteByWhere(w -> {
      w.eq(PrivilegeEntity::getLinkId, form.getLinkId());
      w.eq(PrivilegeEntity::getType, form.getType());
      w.eq(PrivilegeEntity::getAppId, form.getAppId());
    });

    // 重新添加权限
    List<ModuleAction> privileges = form.getPrivileges();
    if (CollectionUtils.isNotEmpty(privileges)) {
      List<PrivilegeEntity> entities = form.getPrivileges()
        .stream().map(m ->
          PrivilegeEntity.create()
            .setLinkId(form.getLinkId())
            .setType(form.getType())
            .setModuleId(m.getModuleId())
            .setActions(m.getActions())
        ).collect(Collectors.toList());
      this.insertBatch(entities);
    }
  }

  /**
   * 查找已经拥有的权限。
   *
   * @param appId  the app id
   * @param type   the type
   * @param linkId the link id
   * @return map
   */
  public Map<String, Set<String>> findHaveAuth(String appId, PrivilegeTypes type, String linkId) {

    List<PrivilegeEntity> scopePrivileges = this.findHavePrivileges(appId, type, linkId);
    Map<String, Set<String>> scopeAuth = this.mergePrivilege(scopePrivileges);
    return scopeAuth;
  }

  public List<PrivilegeEntity> findHavePrivileges(String appId, PrivilegeTypes type, String linkId) {

    List<PrivilegeEntity> scopePrivileges = this.selectList(w -> {
      w.select(PrivilegeEntity::getModuleId);
      w.select(PrivilegeEntity::getModuleCode);
      w.select(PrivilegeEntity::getActions);
      w.eq(PrivilegeEntity::getType, type);
      w.eq(PrivilegeEntity::getLinkId, linkId);
      w.eq(PrivilegeEntity::getAppId, appId);
    });
    return scopePrivileges;
  }

  public Map<String, Set<String>> mergePrivilege(List<PrivilegeEntity> list) {

    Map<String, Set<String>> scopeModules = new HashMap<>();
    for (PrivilegeEntity pe : list) {
      if (scopeModules.containsKey(pe.getModuleId())) {
        Set<String> actions = scopeModules.get(pe.getModuleId());
        actions.addAll(pe.getActions());
      } else {
        Set<String> actions = new HashSet<>(pe.getActions());
        scopeModules.put(pe.getModuleId(), actions);
      }
    }
    return scopeModules;
  }

}
