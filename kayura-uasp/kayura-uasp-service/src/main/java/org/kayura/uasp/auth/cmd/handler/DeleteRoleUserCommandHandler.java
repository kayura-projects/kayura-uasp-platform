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
