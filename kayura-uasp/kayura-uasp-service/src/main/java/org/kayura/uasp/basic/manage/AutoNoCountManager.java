package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.basic.entity.AutoNoCountEntity;
import org.kayura.uasp.basic.mapper.AutoNoCountMapper;
import org.springframework.stereotype.Component;

@Component
public class AutoNoCountManager extends CrudManagerImpl<AutoNoCountMapper, AutoNoCountEntity> {

  protected AutoNoCountManager(AutoNoCountMapper baseMapper) {
    super(baseMapper);
  }

}
