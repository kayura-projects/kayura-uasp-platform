package org.kayura.uasp.workflow.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MockFormMapper extends CrudMapper<MockFormEntity> {
}
