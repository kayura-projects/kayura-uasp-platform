package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.TeamEntity;
import org.kayura.uasp.organize.mapper.TeamMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamManager extends CrudManagerImpl<TeamMapper, TeamEntity> {

  protected TeamManager(TeamMapper baseMapper) {
    super(baseMapper);
  }

}
