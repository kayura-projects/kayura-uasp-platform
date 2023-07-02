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

/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.dev.cmd.UpdateOpsUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserAvatarManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.OpsUserPayload;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateAdminUserCommandHandler implements CommandHandler<UpdateOpsUserCommand, HttpResult> {

  private final UserManager userManager;
  private final PasswordEncoder passwordEncoder;
  private final UserAvatarManager userAvatarManager;

  public UpdateAdminUserCommandHandler(UserManager userManager,
                                       PasswordEncoder passwordEncoder,
                                       UserAvatarManager userAvatarManager) {
    this.userManager = userManager;
    this.passwordEncoder = passwordEncoder;
    this.userAvatarManager = userAvatarManager;
  }

  @Transactional
  public HttpResult execute(UpdateOpsUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OpsUserPayload payload = command.getPayload();
    String userId = Optional.ofNullable(command.getUserId()).orElse(payload.getUserId());

    UserEntity entity = userManager.selectById(userId);
    if (entity == null) {
      return HttpResult.error("更新的用户不存在。");
    }

    if (StringUtils.hasText(payload.getUserName())) {
      if (!payload.getUserName().equalsIgnoreCase(entity.getUserName()) &&
        userManager.selectCount(w -> {
          w.eq(UserEntity::getUserName, payload.getUserName());
        }) > 0) {
        return HttpResult.error("该账号名已经存在。");
      }
    } else {
      payload.setUserName(RandomStringUtils.randomAlphabetic(24));
    }

    if (StringUtils.hasText(payload.getMobile()) &&
      !payload.getUserName().equalsIgnoreCase(entity.getMobile()) &&
      userManager.selectCount(w -> {
        w.eq(UserEntity::getMobile, payload.getMobile());
        w.notEq(UserEntity::getUserId, userId);
      }) > 0) {
      return HttpResult.error("该手机号已被注册。");
    }

    entity.setUserName(payload.getUserName());
    entity.setDisplayName(payload.getDisplayName());
    entity.setAvatar(payload.getAvatar());
    if (StringUtils.hasText(payload.getPassword())) {
      String encode = this.passwordEncoder.encode(payload.getPassword());
      entity.setSalt(RandomStringUtils.randomAlphabetic(32));
      entity.setPassword(encode);
    }
    entity.setEmail(payload.getEmail());
    entity.setMobile(payload.getMobile());
    entity.setAccountExpire(payload.getAccountExpire());
    entity.setEnabled(payload.getEnabled());
    entity.setLocked(payload.getLocked());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    userManager.updateById(userId, entity);

    // avatar
    this.userAvatarManager.writeHistory(
      entity.getUserId(),
      entity.getAvatar(),
      loginUser.getUserId()
    );

    return HttpResult.ok();
  }

}
