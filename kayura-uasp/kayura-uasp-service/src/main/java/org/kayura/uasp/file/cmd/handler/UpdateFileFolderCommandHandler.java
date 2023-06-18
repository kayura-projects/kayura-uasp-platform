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
