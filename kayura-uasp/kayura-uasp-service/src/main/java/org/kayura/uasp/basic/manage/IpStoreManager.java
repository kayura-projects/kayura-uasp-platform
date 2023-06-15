package org.kayura.uasp.basic.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.basic.entity.IpStoreEntity;
import org.kayura.uasp.basic.mapper.IpStoreMapper;
import org.springframework.stereotype.Component;

@Component
public class IpStoreManager extends CrudManagerImpl<IpStoreMapper, IpStoreEntity> {

  protected IpStoreManager(IpStoreMapper baseMapper) {
    super(baseMapper);
  }

}
