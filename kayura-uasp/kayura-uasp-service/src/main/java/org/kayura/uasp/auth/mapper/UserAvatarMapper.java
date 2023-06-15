package org.kayura.uasp.auth.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.auth.entity.UserAvatarEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAvatarMapper extends CrudMapper<UserAvatarEntity> {
}
