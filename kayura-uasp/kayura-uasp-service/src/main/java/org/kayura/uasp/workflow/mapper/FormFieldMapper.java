package org.kayura.uasp.workflow.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.workflow.entity.FormFieldEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FormFieldMapper extends CrudMapper<FormFieldEntity> {
}
