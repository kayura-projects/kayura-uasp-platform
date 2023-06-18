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
