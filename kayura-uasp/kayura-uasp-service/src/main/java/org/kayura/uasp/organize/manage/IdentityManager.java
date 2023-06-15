package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.kayura.uasp.organize.mapper.IdentityMapper;
import org.springframework.stereotype.Component;

@Component
public class IdentityManager extends CrudManagerImpl<IdentityMapper, IdentityEntity> {

  protected IdentityManager(IdentityMapper baseMapper) {
    super(baseMapper);
  }

}
