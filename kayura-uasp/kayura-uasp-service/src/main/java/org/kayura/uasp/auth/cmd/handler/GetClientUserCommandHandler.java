package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.GetClientUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.ClientUserVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetClientUserCommandHandler implements CommandHandler<GetClientUserCommand, HttpResult> {

  private final UserManager userManager;

  public GetClientUserCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetClientUserCommand command) {

    String userId = command.getUserId();

    UserEntity entity = userManager.selectOne(w -> {
      w.eq(UserEntity::getUserId, userId);
      w.eq(UserEntity::getUserType, UserTypes.CLIENT);
    });
    if (entity == null) {
      return HttpResult.error("客户端账号记录不存在。");
    }

    ClientUserVo userVo = ClientUserVo.create()
      .setClientId(entity.getUserId())
      .setClientNo(entity.getUserName())
      .setSecretKey(entity.getPassword())
      .setEnabled(entity.getEnabled())
      .setAccountExpire(entity.getAccountExpire())
      .setDisplayName(entity.getDisplayName())
      .setRemark(entity.getRemark());
    return HttpResult.okBody(userVo);
  }

}
