package org.kayura.uasp.file.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FileLinkMapper extends CrudMapper<FileLinkEntity> {
}
