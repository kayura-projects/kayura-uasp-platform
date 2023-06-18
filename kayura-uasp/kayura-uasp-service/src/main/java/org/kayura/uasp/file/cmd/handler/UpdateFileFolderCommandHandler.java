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
