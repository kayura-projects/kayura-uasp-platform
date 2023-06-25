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
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.DeleteUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, HttpResult> {

  private final UserManager userManager;

  public DeleteUserCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional
  public HttpResult execute(DeleteUserCommand command) {

    IdPayload payload = command.getPayload();
    UserTypes userType = command.getUserType();

    if (CollectionUtils.isEmpty(payload.getIds()) && StringUtils.isBlank(payload.getId())) {
      return HttpResult.error("必需指定一个删除条件。");
    }

    List<String> deleteIds = userManager.selectList(w -> {
      w.select(UserEntity::getUserId);
      if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(UserEntity::getUserId, payload.getIds());
      } else {
        w.eq(UserEntity::getUserId, payload.getId());
      }
      w.eq(UserEntity::getUserType, userType);
    }).stream().map(UserEntity::getUserId).toList();

    // delete
    if (!deleteIds.isEmpty()) {
      userManager.deleteByIds(deleteIds);
    }

    return HttpResult.ok();
  }

}
