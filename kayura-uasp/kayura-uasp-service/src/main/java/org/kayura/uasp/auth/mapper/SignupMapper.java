package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.SignupEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupMapper extends CrudMapper<SignupEntity> {
}
