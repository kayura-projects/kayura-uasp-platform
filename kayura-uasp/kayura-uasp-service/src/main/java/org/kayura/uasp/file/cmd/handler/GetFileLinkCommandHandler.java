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
