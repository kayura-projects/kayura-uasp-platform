package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.UserRetryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRetryMapper extends CrudMapper<UserRetryEntity> {
}
