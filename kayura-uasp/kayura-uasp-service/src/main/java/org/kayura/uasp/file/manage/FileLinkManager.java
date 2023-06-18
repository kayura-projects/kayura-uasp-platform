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
