package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.file.FolderPayload;
import org.kayura.uasp.file.cmd.UpdateFileFolderCommand;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.manage.FileFolderManager;
import org.kayura.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateFileFolderCommandHandler implements CommandHandler<UpdateFileFolderCommand, HttpResult> {

  private final FileFolderManager folderManager;

  public UpdateFileFolderCommandHandler(FileFolderManager folderManager) {
    this.folderManager = folderManager;
  }

  @Transactional
  public HttpResult execute(UpdateFileFolderCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FolderPayload payload = command.getPayload();
    String folderId = Optional.ofNullable(command.getFolderId()).orElse(payload.getFolderId());

    FileFolderEntity entity = folderManager.selectById(payload.getFolderId());
    if (entity == null) {
      return HttpResult.error("更新的数据不存在。");
    }

    folderManager.updateByWhere(w -> {
      w.set(FileFolderEntity::getName, payload.getName());
      w.set(FileFolderEntity::getUpdateTime, DateUtils.now());
      w.eq(FileFolderEntity::getFolderId, folderId);
    });

    return HttpResult.ok();
  }
}
