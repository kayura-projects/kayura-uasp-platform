package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.SelectManagerImpl;
import org.kayura.uasp.organize.entity.MembershipEntity;
import org.kayura.uasp.organize.mapper.MembershipMapper;
import org.springframework.stereotype.Component;

@Component
public class MembershipManager extends SelectManagerImpl<MembershipMapper, MembershipEntity> {

  protected MembershipManager(MembershipMapper baseMapper) {
    super(baseMapper);
  }

}
