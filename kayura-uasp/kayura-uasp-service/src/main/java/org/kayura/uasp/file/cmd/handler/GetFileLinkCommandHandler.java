/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.file.FileLinkVo;
import org.kayura.uasp.file.cmd.GetFileLinkCommand;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.manage.FileFolderManager;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetFileLinkCommandHandler implements CommandHandler<GetFileLinkCommand, HttpResult> {

  private final FileLinkManager linkManager;
  private final FileFolderManager folderManager;
  private final ModelMapper modelMapper;

  public GetFileLinkCommandHandler(FileLinkManager linkManager,
                                   FileFolderManager folderManager,
                                   ModelMapper modelMapper) {
    this.linkManager = linkManager;
    this.folderManager = folderManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetFileLinkCommand command) {

    String linkId = command.getLinkId();

    FileLinkEntity entity = linkManager.selectById(linkId);
    if (entity == null) {
      return HttpResult.error("要获取的记录不存在。");
    }

    FileLinkVo model = modelMapper.map(entity, FileLinkVo.class);
    if (StringUtils.hasText(entity.getFolderId())) {
      String folderPath = folderManager.fullFolderPath(entity.getFolderId());
      model.setFolderPath(folderPath);
    }

    return HttpResult.okBody(model);
  }

}
