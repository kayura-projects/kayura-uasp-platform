package org.kayura.uasp.organize.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper extends CrudMapper<EmployeeEntity> {
}
