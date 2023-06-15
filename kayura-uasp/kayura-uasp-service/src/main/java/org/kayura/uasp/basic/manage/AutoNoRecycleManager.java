package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.basic.entity.AutoNoRecycleEntity;
import org.kayura.uasp.basic.mapper.AutoNoRecycleMapper;
import org.springframework.stereotype.Component;

@Component
public class AutoNoRecycleManager extends CrudManagerImpl<AutoNoRecycleMapper, AutoNoRecycleEntity> {

  protected AutoNoRecycleManager(AutoNoRecycleMapper baseMapper) {
    super(baseMapper);
  }

}
