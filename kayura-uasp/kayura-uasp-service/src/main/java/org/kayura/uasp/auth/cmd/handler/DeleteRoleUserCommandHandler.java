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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.DeleteRoleUserCommand;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.role.RoleUserPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteRoleUserCommandHandler implements CommandHandler<DeleteRoleUserCommand, HttpResult> {

  private final RoleUserManager roleUserManager;

  public DeleteRoleUserCommandHandler(RoleUserManager roleUserManager) {
    this.roleUserManager = roleUserManager;
  }

  @Transactional
  public HttpResult execute(DeleteRoleUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String userId = command.getUserId();
    String roleId = command.getRoleId();

    if (!StringUtils.isAllBlank(userId, roleId)) {

      roleUserManager.deleteByWhere(w -> {
        if (StringUtils.hasText(userId)) {
          w.eq(RoleUserEntity::getUserId, userId);
        }
        if (StringUtils.hasText(roleId)) {
          w.eq(RoleUserEntity::getRoleId, roleId);
        }
      });

    } else {

      RoleUserPayload payload = command.getPayload();
      List<RoleUserPayload> payloads = Optional.ofNullable(command.getPayloads())
        .orElse(payload != null ? Collections.singletonList(payload) : new ArrayList<>());

      if (CollectionUtils.isNotEmpty(payloads)) {
        for (RoleUserPayload item : payloads) {
          if (!StringUtils.isAnyBlank(item.getUserId(), item.getRoleId())) {
            roleUserManager.deleteByWhere(w -> {
              w.eq(RoleUserEntity::getUserId, item.getUserId());
              w.eq(RoleUserEntity::getRoleId, item.getRoleId());
            });
          }
        }
      }
    }

    return HttpResult.ok();
  }

}
