package org.kayura.uasp.dev.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.mapper.ApplicMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicManager extends CrudManagerImpl<ApplicMapper, ApplicEntity> {

  protected ApplicManager(ApplicMapper baseMapper) {
    super(baseMapper);
  }

  public String findAppIdByCode(String appCode) {

    ApplicEntity entity = this.selectOne(w -> {
      w.select(ApplicEntity::getAppId);
      w.eq(ApplicEntity::getCode, appCode);
    });
    return entity != null ? entity.getAppId() : null;
  }

}
