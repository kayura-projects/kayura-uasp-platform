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
