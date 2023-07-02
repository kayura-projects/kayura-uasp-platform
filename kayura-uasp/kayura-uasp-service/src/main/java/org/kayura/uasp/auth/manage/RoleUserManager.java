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
import org.kayura.type.DataStatus;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.mapper.RoleUserMapper;
import org.kayura.uasp.role.RoleTypes;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleUserManager extends CrudManagerImpl<RoleUserMapper, RoleUserEntity> {

  protected RoleUserManager(RoleUserMapper baseMapper) {
    super(baseMapper);
  }

  public List<String> selectRoleCodesByFunc(String appId, String userId) {

    List<String> roleCodes = this.selectList(w -> {
      w.select(RoleUserEntity::getRoleCode);
      w.isNotNull(RoleUserEntity::getRoleCode);
      w.eq(RoleUserEntity::getRoleAppId, appId);
      w.eq(RoleUserEntity::getUserId, userId);
      w.eq(RoleUserEntity::getRoleType, RoleTypes.FUNC);
      w.eq(RoleUserEntity::getRoleStatus, DataStatus.Valid);
    }).stream().map(RoleUserEntity::getRoleCode).toList();
    return roleCodes;
  }
}
