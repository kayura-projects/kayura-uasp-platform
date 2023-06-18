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
