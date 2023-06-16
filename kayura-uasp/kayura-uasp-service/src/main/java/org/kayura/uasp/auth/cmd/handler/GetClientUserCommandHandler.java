/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
