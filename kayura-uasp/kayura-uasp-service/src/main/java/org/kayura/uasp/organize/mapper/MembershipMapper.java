package org.kayura.uasp.organize.mapper;

import org.kayura.mybatis.mapper.SelectMapper;
import org.kayura.uasp.organize.entity.MembershipEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipMapper extends SelectMapper<MembershipEntity> {
}
