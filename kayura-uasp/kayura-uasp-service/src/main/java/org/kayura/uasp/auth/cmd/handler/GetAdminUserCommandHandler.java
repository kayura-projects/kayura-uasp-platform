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
