package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.mapper.RoleMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleManager extends CrudManagerImpl<RoleMapper, RoleEntity> {

  protected RoleManager(RoleMapper baseMapper) {
    super(baseMapper);
  }

}
