package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.basic.entity.DictDefineEntity;
import org.kayura.uasp.basic.mapper.DictDefineMapper;
import org.springframework.stereotype.Component;

@Component
public class DictDefineManager extends CrudManagerImpl<DictDefineMapper, DictDefineEntity> {

  protected DictDefineManager(DictDefineMapper baseMapper) {
    super(baseMapper);
  }

}
