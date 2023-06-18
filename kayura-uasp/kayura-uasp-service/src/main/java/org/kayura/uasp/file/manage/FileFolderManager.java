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
