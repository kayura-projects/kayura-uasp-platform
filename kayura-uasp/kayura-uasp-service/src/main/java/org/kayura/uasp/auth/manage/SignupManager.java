package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.auth.entity.SignupEntity;
import org.kayura.uasp.auth.mapper.SignupMapper;
import org.springframework.stereotype.Component;

@Component
public class SignupManager extends CrudManagerImpl<SignupMapper, SignupEntity> {

  protected SignupManager(SignupMapper baseMapper) {
    super(baseMapper);
  }

}
