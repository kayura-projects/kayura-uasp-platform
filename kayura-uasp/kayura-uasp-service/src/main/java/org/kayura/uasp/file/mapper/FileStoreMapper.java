package org.kayura.uasp.file.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.file.entity.FileStoreEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStoreMapper extends CrudMapper<FileStoreEntity> {
}
