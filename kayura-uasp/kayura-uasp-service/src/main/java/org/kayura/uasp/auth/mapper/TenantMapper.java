package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantMapper extends CrudMapper<TenantEntity> {
}
