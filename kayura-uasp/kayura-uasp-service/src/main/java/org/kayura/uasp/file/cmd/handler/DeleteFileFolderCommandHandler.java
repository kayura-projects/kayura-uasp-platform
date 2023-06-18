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
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.file.cmd.DeleteFileFolderCommand;
import org.kayura.uasp.file.entity.FileFolderEntity;
import org.kayura.uasp.file.manage.FileFolderManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteFileFolderCommandHandler implements CommandHandler<DeleteFileFolderCommand, HttpResult> {

  public static final String DELETE_EXISTS_CHILDREN = "存在子目录，不允许删除。";
  private final FileFolderManager folderManager;

  public DeleteFileFolderCommandHandler(FileFolderManager folderManager) {
    this.folderManager = folderManager;
  }

  @Transactional
  public HttpResult execute(DeleteFileFolderCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String folderId = Optional.ofNullable(command.getFolderId()).orElse(payload.getId());

    // 不能删除有子目录的
    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      for (String id : payload.getIds()) {
        if (this.deleteFolder(id)) {
          return HttpResult.error(DELETE_EXISTS_CHILDREN);
        }
      }
    } else if (StringUtils.hasText(folderId)) {
      if (this.deleteFolder(folderId)) {
        return HttpResult.error(DELETE_EXISTS_CHILDREN);
      }
    }

    return HttpResult.ok();
  }

  private boolean deleteFolder(String id) {

    if (folderManager.selectCount(w ->
      w.eq(FileFolderEntity::getParentId, id)
    ) > 0) {
      return true;
    }

    folderManager.deleteById(id);
    return false;
  }
}
