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
