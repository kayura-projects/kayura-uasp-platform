package org.kayura.uasp.dev.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.dev.mapper.ModuleDefineMapper;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.springframework.stereotype.Component;

@Component
public class ModuleDefineManager extends CrudManagerImpl<ModuleDefineMapper, ModuleDefineEntity> {

  protected ModuleDefineManager(ModuleDefineMapper baseMapper) {
    super(baseMapper);
  }

}
