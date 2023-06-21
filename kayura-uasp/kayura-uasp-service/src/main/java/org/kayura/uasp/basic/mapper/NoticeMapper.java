package org.kayura.uasp.basic.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.basic.entity.NoticeEntity;
import org.springframework.stereotype.Repository;

/**
 * 通知公告 数据访问接口定义。
 *
 * @author liangxia@live.com
 */
@Repository
public interface NoticeMapper extends CrudMapper<NoticeEntity> {

}
