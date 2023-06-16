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
