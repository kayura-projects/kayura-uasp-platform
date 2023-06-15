package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.CreateClientUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.ClientUserPayload;
import org.kayura.uasp.user.ClientUserVo;
import org.kayura.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateClientUserCommandHandler implements CommandHandler<CreateClientUserCommand, HttpResult> {

  private final UserManager userManager;
  private final ModelMapper modelMapper;

  public CreateClientUserCommandHandler(UserManager userManager,
                                        ModelMapper modelMapper) {
    this.userManager = userManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateClientUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ClientUserPayload payload = command.getPayload();

    if (userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getClientNo())) > 0) {
      return HttpResult.error("客户端账号已经重复。");
    }

    UserEntity entity = UserEntity.create()
      .setUserId(userManager.nextId())
      .setUserType(UserTypes.CLIENT)
      .setUserName(payload.getClientNo())
      .setPassword(payload.getSecretKey())
      .setDisplayName(payload.getDisplayName())
      .setEnabled(payload.getEnabled())
      .setAccountExpire(payload.getAccountExpire())
      .setLocked(Boolean.FALSE)
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setRemark(payload.getRemark());
    userManager.insertOne(entity);

    ClientUserVo model = modelMapper.map(entity, ClientUserVo.class);
    return HttpResult.okBody(model);
  }

}
