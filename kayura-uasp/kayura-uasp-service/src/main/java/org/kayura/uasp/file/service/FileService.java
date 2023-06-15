package org.kayura.uasp.file.service;

import org.kayura.uasp.file.entity.FileStoreEntity;
import org.kayura.uasp.file.manage.FileStoreManager;
import org.kayura.uasp.file.utils.FileConst;
import org.kayura.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class FileService implements FileConst {

  private final FileStoreManager storeManager;

  public FileService(FileStoreManager storeManager) {
    this.storeManager = storeManager;
  }

  @Transactional
  public void correctFileExists(List<String> fileIds) {

    storeManager.updateByWhere(w -> {
      w.set(FileStoreEntity::getExists, Boolean.FALSE);
    });

    if (CollectionUtils.isNotEmpty(fileIds)) {
      if (fileIds.size() > 500) {
        List<Collection<String>> splitList = CollectionUtils.splitList(fileIds, 500);
        for (Collection<String> collection : splitList) {
          storeManager.updateByWhere(w -> {
            w.set(FileStoreEntity::getExists, Boolean.TRUE);
            w.in(FileStoreEntity::getFileId, collection);
          });
        }
      } else {
        storeManager.updateByWhere(w -> {
          w.set(FileStoreEntity::getExists, Boolean.TRUE);
          w.in(FileStoreEntity::getFileId, fileIds);
        });
      }
    }

  }

}
