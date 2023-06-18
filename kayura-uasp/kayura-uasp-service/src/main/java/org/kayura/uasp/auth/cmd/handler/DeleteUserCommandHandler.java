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
