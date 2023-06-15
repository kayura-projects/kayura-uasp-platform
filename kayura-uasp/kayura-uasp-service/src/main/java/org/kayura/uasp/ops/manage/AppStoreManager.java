package org.kayura.uasp.ops.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.kayura.uasp.ops.mapper.AppStoreMapper;
import org.springframework.stereotype.Component;

@Component
public class AppStoreManager extends CrudManagerImpl<AppStoreMapper, AppStoreEntity> {

  protected AppStoreManager(AppStoreMapper baseMapper) {
    super(baseMapper);
  }

}
