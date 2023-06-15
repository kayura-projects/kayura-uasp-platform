package org.kayura.uasp.organize.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.organize.entity.TeamEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMapper extends CrudMapper<TeamEntity> {
}
