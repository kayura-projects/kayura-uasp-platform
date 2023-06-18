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
