package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.TeamUserEntity;
import org.kayura.uasp.organize.mapper.TeamUserMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamUserManager extends CrudManagerImpl<TeamUserMapper, TeamUserEntity> {

  protected TeamUserManager(TeamUserMapper baseMapper) {
    super(baseMapper);
  }

}
