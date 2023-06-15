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
