/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
