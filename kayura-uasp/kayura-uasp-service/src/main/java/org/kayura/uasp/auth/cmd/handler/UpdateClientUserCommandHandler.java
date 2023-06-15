package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.UpdateClientUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.ClientUserPayload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateClientUserCommandHandler implements CommandHandler<UpdateClientUserCommand, HttpResult> {

  private final UserManager userManager;

  public UpdateClientUserCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional
  public HttpResult execute(UpdateClientUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ClientUserPayload payload = command.getPayload();
    String clientId = Optional.ofNullable(command.getClientId()).orElse(payload.getClientId());

    UserEntity entity = userManager.selectById(clientId);
    if (entity == null) {
      return HttpResult.error("更新的账号不存在。");
    }

    entity.setPassword(payload.getSecretKey());
    entity.setAccountExpire(payload.getAccountExpire());
    entity.setEnabled(payload.getEnabled());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    userManager.updateById(clientId, entity);

    return HttpResult.ok();
  }

}
