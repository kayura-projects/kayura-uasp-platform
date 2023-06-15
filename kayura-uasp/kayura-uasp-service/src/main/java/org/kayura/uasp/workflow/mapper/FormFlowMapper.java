package org.kayura.uasp.workflow.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.workflow.entity.FormFlowEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FormFlowMapper extends CrudMapper<FormFlowEntity> {
}
