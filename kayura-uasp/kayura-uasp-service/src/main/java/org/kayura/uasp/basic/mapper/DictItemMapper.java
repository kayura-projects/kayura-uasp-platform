package org.kayura.uasp.basic.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DictItemMapper extends CrudMapper<DictItemEntity> {
}
