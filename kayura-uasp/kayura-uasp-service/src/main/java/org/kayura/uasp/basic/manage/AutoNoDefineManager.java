package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.basic.entity.AutoNoDefineEntity;
import org.kayura.uasp.basic.mapper.AutoNoDefineMapper;
import org.springframework.stereotype.Component;

@Component
public class AutoNoDefineManager extends CrudManagerImpl<AutoNoDefineMapper, AutoNoDefineEntity> {

  protected AutoNoDefineManager(AutoNoDefineMapper baseMapper) {
    super(baseMapper);
  }

}
