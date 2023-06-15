package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.CreateRoleUserCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.role.RoleUserPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CreateRoleUserCommandHandler implements CommandHandler<CreateRoleUserCommand, HttpResult> {

  private final UserManager userManager;
  private final RoleManager roleManager;
  private final RoleUserManager roleUserManager;

  public CreateRoleUserCommandHandler(UserManager userManager,
                                      RoleManager roleManager,
                                      RoleUserManager roleUserManager) {
    this.userManager = userManager;
    this.roleManager = roleManager;
    this.roleUserManager = roleUserManager;
  }

  @Transactional
  public HttpResult execute(CreateRoleUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    RoleUserPayload payload = command.getPayload();
    List<RoleUserPayload> payloads = Optional.ofNullable(command.getPayloads())
      .orElse(Collections.singletonList(payload));

    if (CollectionUtils.isNotEmpty(payloads)) {

      final List<String> userIds = payloads.stream()
        .map(RoleUserPayload::getUserId)
        .filter(StringUtils::hasText).distinct().toList();
      if (userIds.isEmpty()) {
        return HttpResult.error("缺少用户ID数据。");
      }
      final List<String> roleIds = payloads.stream()
        .map(RoleUserPayload::getRoleId)
        .filter(StringUtils::hasText).distinct().toList();
      if (roleIds.isEmpty()) {
        return HttpResult.error("缺少角色ID数据。");
      }

      List<String> existsUserIds = userManager.selectList(w -> {
        w.select(UserEntity::getUserId);
        w.in(UserEntity::getUserId, userIds);
      }).stream().map(UserEntity::getUserId).toList();

      List<String> existsRoleIds = roleManager.selectList(w -> {
        w.select(RoleEntity::getRoleId);
        w.in(RoleEntity::getRoleId, roleIds);
      }).stream().map(RoleEntity::getRoleId).toList();

      for (RoleUserPayload saveItem : payloads) {
        if (existsUserIds.contains(saveItem.getUserId()) && existsRoleIds.contains(saveItem.getRoleId())) {
          RoleUserEntity entity = RoleUserEntity.create();
          entity.setUserId(saveItem.getUserId());
          entity.setRoleId(saveItem.getRoleId());
          entity.setCreatorId(loginUser.getUserId());
          entity.setCreateTime(DateUtils.now());
          roleUserManager.insertOne(entity);
        }
      }
    }

    return HttpResult.ok();
  }

}
