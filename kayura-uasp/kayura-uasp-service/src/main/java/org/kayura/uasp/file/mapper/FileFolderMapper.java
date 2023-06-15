package org.kayura.uasp.file.mapper;

import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FileFolderMapper extends CrudMapper<FileFolderEntity> {
}
