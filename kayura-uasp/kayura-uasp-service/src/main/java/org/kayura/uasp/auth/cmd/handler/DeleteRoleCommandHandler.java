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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.DeleteRoleCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DeleteRoleCommandHandler implements CommandHandler<DeleteRoleCommand, HttpResult> {

  private final RoleManager roleManager;

  public DeleteRoleCommandHandler(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  @Transactional
  public HttpResult execute(DeleteRoleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = command.getPayload();

    if (CollectionUtils.isEmpty(payload.getIds()) && StringUtils.isBlank(payload.getId())) {
      return HttpResult.error("必需指定一个删除条件。");
    }

    List<String> deleteIds = roleManager.selectList(w -> {
      w.select(RoleEntity::getRoleId);
      if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(RoleEntity::getRoleId, payload.getIds());
      } else {
        w.eq(RoleEntity::getRoleId, payload.getId());
      }
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.eq(RoleEntity::getTenantId, loginUser.getTenantId());
      }
    }).stream().map(RoleEntity::getRoleId).toList();

    // delete
    if (!deleteIds.isEmpty()) {
      roleManager.deleteByIds(deleteIds);
    }

    return HttpResult.ok();
  }

}
