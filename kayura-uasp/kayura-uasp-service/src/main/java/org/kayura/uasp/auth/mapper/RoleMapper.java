package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends CrudMapper<RoleEntity> {
}
