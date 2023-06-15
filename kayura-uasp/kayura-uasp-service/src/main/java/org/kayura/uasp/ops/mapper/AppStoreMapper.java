package org.kayura.uasp.ops.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AppStoreMapper extends CrudMapper<AppStoreEntity> {
}
