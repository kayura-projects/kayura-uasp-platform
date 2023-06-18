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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.DeleteRoleUserCommand;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.role.RoleUserPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteRoleUserCommandHandler implements CommandHandler<DeleteRoleUserCommand, HttpResult> {

  private final RoleUserManager roleUserManager;

  public DeleteRoleUserCommandHandler(RoleUserManager roleUserManager) {
    this.roleUserManager = roleUserManager;
  }

  @Transactional
  public HttpResult execute(DeleteRoleUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String userId = command.getUserId();
    String roleId = command.getRoleId();

    if (!StringUtils.isAllBlank(userId, roleId)) {

      roleUserManager.deleteByWhere(w -> {
        if (StringUtils.hasText(userId)) {
          w.eq(RoleUserEntity::getUserId, userId);
        }
        if (StringUtils.hasText(roleId)) {
          w.eq(RoleUserEntity::getRoleId, roleId);
        }
      });

    } else {

      RoleUserPayload payload = command.getPayload();
      List<RoleUserPayload> payloads = Optional.ofNullable(command.getPayloads())
        .orElse(payload != null ? Collections.singletonList(payload) : new ArrayList<>());

      if (CollectionUtils.isNotEmpty(payloads)) {
        for (RoleUserPayload item : payloads) {
          if (!StringUtils.isAnyBlank(item.getUserId(), item.getRoleId())) {
            roleUserManager.deleteByWhere(w -> {
              w.eq(RoleUserEntity::getUserId, item.getUserId());
              w.eq(RoleUserEntity::getRoleId, item.getRoleId());
            });
          }
        }
      }
    }

    return HttpResult.ok();
  }

}
