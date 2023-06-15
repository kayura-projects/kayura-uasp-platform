package org.kayura.uasp.file.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.mapper.FileFolderMapper;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FileFolderManager extends CrudManagerImpl<FileFolderMapper, FileFolderEntity> {

  protected FileFolderManager(FileFolderMapper baseMapper) {
    super(baseMapper);
  }

  public String fullFolderPath(String folderId) {

    StringBuilder sb = this.makeParentName(new StringBuilder(), folderId);
    sb.insert(0, "我的目录");
    return sb.toString();
  }

  StringBuilder makeParentName(StringBuilder sb, String parentId) {

    FileFolderEntity folder = this.selectOne(w -> {
      w.select(FileFolderEntity::getParentId);
      w.select(FileFolderEntity::getName);
      w.eq(FileFolderEntity::getFolderId, parentId);
    });
    if (folder != null) {
      sb.insert(0, folder.getName()).insert(0, "/");
      if (StringUtils.hasText(folder.getParentId())) {
        makeParentName(sb, folder.getParentId());
      }
    }
    return sb;
  }
}
