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
import org.kayura.uasp.file.DownloadFile;
import org.kayura.uasp.file.FileLinkVo;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.mapper.FileLinkMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class FileLinkManager extends CrudManagerImpl<FileLinkMapper, FileLinkEntity> {

  private final ModelMapper modelMapper;

  protected FileLinkManager(FileLinkMapper baseMapper, ModelMapper modelMapper) {
    super(baseMapper);
    this.modelMapper = modelMapper;
  }

  public List<FileLinkVo> queryLinksForBusinessKey(String businessKey) {

    List<FileLinkEntity> entities = this.selectList(w -> w.eq(FileLinkEntity::getBusinessKey, businessKey));
    return entities.stream().map(m -> modelMapper.map(m, FileLinkVo.class)).toList();
  }

  public List<FileLinkVo> queryForIds(Collection<String> attachmentIds) {
    List<FileLinkEntity> entities = this.selectList(w -> w.in(FileLinkEntity::getLinkId, attachmentIds));
    return entities.stream().map(m -> modelMapper.map(m, FileLinkVo.class)).toList();
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

  public void updateDownloads(Collection<FileLinkEntity> links) {

    for (FileLinkEntity item : links) {
      this.updateByWhere(w -> {
        w.set(FileLinkEntity::getDownloads, item.getDownloads() + 1);
        w.eq(FileLinkEntity::getLinkId, item.getLinkId());
      });
    }
  }

}
