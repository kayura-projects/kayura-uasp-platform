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
import org.kayura.uasp.auth.cmd.GetAdminUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserAvatarManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.AdminUserVo;
import org.kayura.uasp.user.UserAvatarVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAdminUserCommandHandler implements CommandHandler<GetAdminUserCommand, HttpResult> {

  private final UserManager userManager;
  private final ModelMapper modelMapper;
  private final UserAvatarManager userAvatarManager;

  public GetAdminUserCommandHandler(UserManager userManager,
                                    ModelMapper modelMapper,
                                    UserAvatarManager userAvatarManager) {
    this.userManager = userManager;
    this.modelMapper = modelMapper;
    this.userAvatarManager = userAvatarManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetAdminUserCommand command) {

    String userId = command.getUserId();

    AdminUserVo model = null;
    UserEntity entity = userManager.selectById(userId);
    if (entity != null) {
      model = modelMapper.map(entity, AdminUserVo.class);
      // History Avatars
      List<UserAvatarVo> avatars = this.userAvatarManager.queryHistory(userId);
      model.setHistoryAvatars(avatars);
    }
    return HttpResult.okBody(model);
  }
}
