package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.DepartLeaderEntity;
import org.kayura.uasp.organize.mapper.DepartLeaderMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartLeaderManager extends CrudManagerImpl<DepartLeaderMapper, DepartLeaderEntity> {

  protected DepartLeaderManager(DepartLeaderMapper baseMapper) {
    super(baseMapper);
  }

}
