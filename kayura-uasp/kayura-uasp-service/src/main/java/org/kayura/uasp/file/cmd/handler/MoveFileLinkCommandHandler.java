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
import org.kayura.uasp.file.MoveFilePayload;
import org.kayura.uasp.file.cmd.MoveFileLinkCommand;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MoveFileLinkCommandHandler implements CommandHandler<MoveFileLinkCommand, HttpResult> {

  private final FileLinkManager linkManager;

  public MoveFileLinkCommandHandler(FileLinkManager linkManager) {
    this.linkManager = linkManager;
  }

  @Transactional
  public HttpResult execute(MoveFileLinkCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MoveFilePayload payload = command.getPayload();

    linkManager.updateByWhere(w -> {
      w.set(FileLinkEntity::getFolderId, payload.getFolderId());
      w.in(FileLinkEntity::getLinkId, payload.getLinkIds());
    });

    return HttpResult.ok();
  }
}
