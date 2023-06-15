package org.kayura.uasp.organize.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityMapper extends CrudMapper<IdentityEntity> {
}
