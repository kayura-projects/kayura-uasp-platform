/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.basic.mapper.DictItemMapper;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictItemManager extends CrudManagerImpl<DictItemMapper, DictItemEntity> {

  protected DictItemManager(DictItemMapper baseMapper) {
    super(baseMapper);
  }

  public List<DictItemEntity> chooseDictItems(String defineCode, LoginUser loginUser, String tenantId) {

    List<DictItemEntity> entities = this.selectList(w -> {
      w.select(DictItemEntity::getItemId);
      w.select(DictItemEntity::getCode);
      w.select(DictItemEntity::getName);
      // 普通用户拿自己的租户ID，后台管理拿指定的或为空的。
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.and(x -> x.eq(DictItemEntity::getTenantId, loginUser.getTenantId()).or(y -> y.isNull(DictItemEntity::getTenantId)));
      } else if (StringUtils.hasText(tenantId)) {
        w.and(x -> x.eq(DictItemEntity::getTenantId, tenantId).or(y -> y.isNull(DictItemEntity::getTenantId)));
      } else {
        w.isNull(DictItemEntity::getTenantId);
      }
      w.eq(DictItemEntity::getDefineCode, defineCode);
      w.eq(DictItemEntity::getStatus, DataStatus.Valid);
    });
    return entities;
  }

}
