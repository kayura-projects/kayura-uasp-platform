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
