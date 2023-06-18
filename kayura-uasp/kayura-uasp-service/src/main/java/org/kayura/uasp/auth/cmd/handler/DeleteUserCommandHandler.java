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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.DeleteUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, HttpResult> {

  private final UserManager userManager;

  public DeleteUserCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional
  public HttpResult execute(DeleteUserCommand command) {

    IdPayload payload = command.getPayload();
    UserTypes userType = command.getUserType();

    if (CollectionUtils.isEmpty(payload.getIds()) && StringUtils.isBlank(payload.getId())) {
      return HttpResult.error("必需指定一个删除条件。");
    }

    List<String> deleteIds = userManager.selectList(w -> {
      w.select(UserEntity::getUserId);
      if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(UserEntity::getUserId, payload.getIds());
      } else {
        w.eq(UserEntity::getUserId, payload.getId());
      }
      w.eq(UserEntity::getUserType, userType);
    }).stream().map(UserEntity::getUserId).toList();

    // delete
    if (!deleteIds.isEmpty()) {
      userManager.deleteByIds(deleteIds);
    }

    return HttpResult.ok();
  }

}
