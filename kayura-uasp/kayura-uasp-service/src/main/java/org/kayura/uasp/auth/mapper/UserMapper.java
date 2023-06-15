package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends CrudMapper<UserEntity> {
}
