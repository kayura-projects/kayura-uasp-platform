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
