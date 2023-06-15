package org.kayura.uasp.organize.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMapper extends CrudMapper<CompanyEntity> {
}
