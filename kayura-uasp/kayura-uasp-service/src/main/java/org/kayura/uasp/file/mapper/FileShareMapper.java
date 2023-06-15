package org.kayura.uasp.file.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.file.entity.FileShareEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FileShareMapper extends CrudMapper<FileShareEntity> {
}
