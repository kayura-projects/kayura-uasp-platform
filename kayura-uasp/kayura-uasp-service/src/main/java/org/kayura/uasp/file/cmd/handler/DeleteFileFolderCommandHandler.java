/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
