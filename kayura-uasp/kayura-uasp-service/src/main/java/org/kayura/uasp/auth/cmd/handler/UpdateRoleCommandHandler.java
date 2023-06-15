package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.UpdateRoleCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.role.RolePayload;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateRoleCommandHandler implements CommandHandler<UpdateRoleCommand, HttpResult> {

  private final RoleManager roleManager;

  public UpdateRoleCommandHandler(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  @Transactional
  public HttpResult execute(UpdateRoleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    RolePayload payload = command.getPayload();
    RoleTypes roleType = command.getRoleType();
    String roleId = Optional.ofNullable(command.getRoleId()).orElse(payload.getRoleId());

    RoleEntity entity = roleManager.selectOne(w -> {
      w.eq(RoleEntity::getRoleId, roleId);
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.eq(RoleEntity::getTenantId, loginUser.getTenantId());
      }
    });
    if (entity == null) {
      return HttpResult.error("更新的对象不存在。");
    }


    if (StringUtils.hasText(payload.getCode())) {
      if (!payload.getCode().equalsIgnoreCase(entity.getCode()) &&
        roleManager.selectCount(w -> w.eq(RoleEntity::getCode, payload.getCode())) > 0) {
        return HttpResult.error("编号已经被占用。");
      }
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setSort(payload.getSort());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    roleManager.updateById(roleId, entity);

    return HttpResult.ok();
  }

}
