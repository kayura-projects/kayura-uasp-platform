package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.PrivilegeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeMapper extends CrudMapper<PrivilegeEntity> {
}
