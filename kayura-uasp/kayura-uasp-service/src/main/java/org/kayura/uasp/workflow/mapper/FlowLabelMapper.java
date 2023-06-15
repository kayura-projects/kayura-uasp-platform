package org.kayura.uasp.workflow.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.workflow.entity.FlowLabelEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowLabelMapper extends CrudMapper<FlowLabelEntity> {
}
