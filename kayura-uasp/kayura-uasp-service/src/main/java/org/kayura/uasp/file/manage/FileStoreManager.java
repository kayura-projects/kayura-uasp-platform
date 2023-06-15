package org.kayura.uasp.file.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.file.entity.FileStoreEntity;
import org.kayura.uasp.file.mapper.FileStoreMapper;
import org.springframework.stereotype.Component;

@Component
public class FileStoreManager extends CrudManagerImpl<FileStoreMapper, FileStoreEntity> {

  protected FileStoreManager(FileStoreMapper baseMapper) {
    super(baseMapper);
  }

}
