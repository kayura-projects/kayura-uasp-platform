package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.auth.mapper.TenantMapper;
import org.springframework.stereotype.Component;

@Component
public class TenantManager extends CrudManagerImpl<TenantMapper, TenantEntity> {

  protected TenantManager(TenantMapper baseMapper) {
    super(baseMapper);
  }

}
