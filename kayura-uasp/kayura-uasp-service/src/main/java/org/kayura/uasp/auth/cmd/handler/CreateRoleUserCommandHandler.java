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
