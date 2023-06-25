/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
