package org.kayura.uasp.file.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.file.entity.FileShareEntity;
import org.kayura.uasp.file.mapper.FileShareMapper;
import org.springframework.stereotype.Component;

@Component
public class FileShareManager extends CrudManagerImpl<FileShareMapper, FileShareEntity> {

  protected FileShareManager(FileShareMapper baseMapper) {
    super(baseMapper);
  }

}
