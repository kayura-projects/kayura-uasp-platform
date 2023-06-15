package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.auth.entity.UserRetryEntity;
import org.kayura.uasp.auth.mapper.UserRetryMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRetryManager extends CrudManagerImpl<UserRetryMapper, UserRetryEntity> {

  protected UserRetryManager(UserRetryMapper baseMapper) {
    super(baseMapper);
  }

}
