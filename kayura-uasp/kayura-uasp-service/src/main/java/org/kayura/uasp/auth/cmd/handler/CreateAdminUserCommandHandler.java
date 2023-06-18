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
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.CreateAdminUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserAvatarManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.AdminUserPayload;
import org.kayura.uasp.user.AdminUserVo;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateAdminUserCommandHandler implements CommandHandler<CreateAdminUserCommand, HttpResult> {

  private final UserManager userManager;
  private final UserAvatarManager userAvatarManager;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  public CreateAdminUserCommandHandler(UserManager userManager,
                                       UserAvatarManager userAvatarManager,
                                       ModelMapper modelMapper,
                                       PasswordEncoder passwordEncoder) {
    this.userManager = userManager;
    this.userAvatarManager = userAvatarManager;
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public HttpResult execute(CreateAdminUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AdminUserPayload payload = command.getPayload();

    // userName
    if (userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getUserName())) > 0) {
      return HttpResult.error("该账号名已经存在。");
    }

    // mobile
    if (StringUtils.hasText(payload.getMobile()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getMobile, payload.getMobile())) > 0) {
      return HttpResult.error("该手机号已被注册。");
    }

    // 若未指定昵称，就使用手机号或账号
    if (StringUtils.isBlank(payload.getDisplayName())) {
      payload.setDisplayName(Optional.ofNullable(payload.getMobile()).orElse(payload.getUserName()));
    }

    UserEntity entity = UserEntity.create();
    entity.setUserId(userManager.nextId());
    entity.setUserType(UserTypes.ADMIN);
    entity.setUserName(payload.getUserName());
    entity.setDisplayName(payload.getDisplayName());
    entity.setAvatar(payload.getAvatar());
    entity.setSalt(RandomStringUtils.randomAlphabetic(32));
    if (StringUtils.hasText(payload.getPassword())) {
      String encode = this.passwordEncoder.encode(payload.getPassword());
      entity.setPassword(encode);
    }
    entity.setEmail(payload.getEmail());
    entity.setMobile(payload.getMobile());
    entity.setLocked(payload.getLocked());
    entity.setAccountExpire(payload.getAccountExpire());
    entity.setEnabled(payload.getEnabled());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(DateUtils.now());
    userManager.insertOne(entity);

    // avatar
    this.userAvatarManager.writeHistory(
      entity.getUserId(),
      entity.getAvatar(),
      loginUser.getUserId()
    );

    // result
    AdminUserVo model = modelMapper.map(entity, AdminUserVo.class);
    return HttpResult.okBody(model);
  }

}
