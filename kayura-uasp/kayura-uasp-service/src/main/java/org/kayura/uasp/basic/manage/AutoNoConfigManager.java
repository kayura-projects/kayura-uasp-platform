package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.mapper.AutoNoConfigMapper;
import org.springframework.stereotype.Component;

@Component
public class AutoNoConfigManager extends CrudManagerImpl<AutoNoConfigMapper, AutoNoConfigEntity> {

  protected AutoNoConfigManager(AutoNoConfigMapper baseMapper) {
    super(baseMapper);
  }

}
