package org.kayura.uasp.basic.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.basic.entity.AutoNoCountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoNoCountMapper extends CrudMapper<AutoNoCountEntity> {
}
