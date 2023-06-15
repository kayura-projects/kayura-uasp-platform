package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.file.FolderPayload;
import org.kayura.uasp.file.cmd.CreateFileFolderCommand;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.manage.FileFolderManager;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateFileFolderCommandHandler implements CommandHandler<CreateFileFolderCommand, HttpResult> {

  private final FileFolderManager folderManager;

  public CreateFileFolderCommandHandler(FileFolderManager folderManager) {
    this.folderManager = folderManager;
  }

  @Transactional
  public HttpResult execute(CreateFileFolderCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FolderPayload payload = command.getPayload();

    payload.setOwnerId(loginUser.getUserId());
    if (StringUtils.hasText(loginUser.getTenantId())) {
      payload.setTenantId(loginUser.getTenantId());
    }

    FileFolderEntity entity = FileFolderEntity.create()
      .setFolderId(folderManager.nextId())
      .setParentId(payload.getParentId())
      .setTenantId(payload.getTenantId())
      .setOwnerType(payload.getOwnerType())
      .setOwnerId(payload.getOwnerId())
      .setCreateTime(DateUtils.now())
      .setName(payload.getName());
    folderManager.insertOne(entity);

    return HttpResult.ok();
  }
}
