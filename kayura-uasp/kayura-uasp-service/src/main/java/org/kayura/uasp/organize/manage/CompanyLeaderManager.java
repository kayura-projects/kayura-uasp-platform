package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.CompanyLeaderEntity;
import org.kayura.uasp.organize.mapper.CompanyLeaderMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyLeaderManager extends CrudManagerImpl<CompanyLeaderMapper, CompanyLeaderEntity> {

  protected CompanyLeaderManager(CompanyLeaderMapper baseMapper) {
    super(baseMapper);
  }

}
