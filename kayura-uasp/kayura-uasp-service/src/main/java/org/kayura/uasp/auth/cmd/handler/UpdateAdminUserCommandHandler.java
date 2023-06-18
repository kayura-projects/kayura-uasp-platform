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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.UpdateAdminUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserAvatarManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.AdminUserPayload;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateAdminUserCommandHandler implements CommandHandler<UpdateAdminUserCommand, HttpResult> {

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
  public HttpResult execute(UpdateAdminUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AdminUserPayload payload = command.getPayload();
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
    entity.setSalt(RandomStringUtils.randomAlphabetic(32));
    if (StringUtils.hasText(payload.getPassword())) {
      String encode = this.passwordEncoder.encode(payload.getPassword());
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
