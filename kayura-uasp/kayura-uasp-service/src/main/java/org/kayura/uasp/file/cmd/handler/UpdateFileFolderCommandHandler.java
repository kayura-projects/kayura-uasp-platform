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
