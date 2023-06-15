package org.kayura.uasp.organize.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.organize.entity.TeamUserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamUserMapper extends CrudMapper<TeamUserEntity> {
}
