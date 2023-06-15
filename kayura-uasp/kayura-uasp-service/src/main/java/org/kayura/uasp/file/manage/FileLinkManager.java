package org.kayura.uasp.file.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.mapper.FileLinkMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class FileLinkManager extends CrudManagerImpl<FileLinkMapper, FileLinkEntity> {

  private final ModelMapper modelMapper;

  protected FileLinkManager(FileLinkMapper baseMapper, ModelMapper modelMapper) {
    super(baseMapper);
    this.modelMapper = modelMapper;
  }

  public DownloadFile downloadFile(String linkId) {

    DownloadFile model = null;
    FileLinkEntity entity = this.selectById(linkId);
    if (entity != null) {
      updateDownloads(Collections.singletonList(entity));
      model = modelMapper.map(entity, DownloadFile.class);
    }
    return model;
  }

  public void updateDownloads(List<FileLinkEntity> links) {

    for (FileLinkEntity item : links) {
      this.updateByWhere(w -> {
        w.set(FileLinkEntity::getDownloads, item.getDownloads() + 1);
        w.eq(FileLinkEntity::getLinkId, item.getLinkId());
      });
    }
  }

}
