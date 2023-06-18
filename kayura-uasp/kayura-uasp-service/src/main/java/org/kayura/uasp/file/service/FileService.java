/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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
