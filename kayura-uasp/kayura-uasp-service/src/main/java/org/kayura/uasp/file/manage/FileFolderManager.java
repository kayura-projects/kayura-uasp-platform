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
