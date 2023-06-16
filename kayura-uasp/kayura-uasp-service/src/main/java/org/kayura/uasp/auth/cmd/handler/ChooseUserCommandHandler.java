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
import org.kayura.type.SelectItem;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.ChooseUserCommand;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ChooseUserCommandHandler implements CommandHandler<ChooseUserCommand, HttpResult> {

  private final RoleUserManager roleUserManager;
  private final UserManager userManager;

  public ChooseUserCommandHandler(RoleUserManager roleUserManager,
                                  UserManager userManager) {
    this.roleUserManager = roleUserManager;
    this.userManager = userManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseUserCommand command) {

    String roleId = command.getRoleId();
    String tenantId = command.getTenantId();
    List<UserTypes> userTypes = command.getUserTypes();

    Set<String> notUserIds;
    if (StringUtils.hasText(roleId)) {
      notUserIds = roleUserManager.selectList(w -> {
        w.select(RoleUserEntity::getUserId);
        w.eq(RoleUserEntity::getRoleId, roleId);
      }).stream().map(RoleUserEntity::getUserId).collect(Collectors.toSet());
    } else {
      notUserIds = null;
    }

    List<UserEntity> entities = userManager.selectList(w -> {
      if (CollectionUtils.isNotEmpty(notUserIds)) {
        w.notIn(UserEntity::getUserId, notUserIds);
      }
      if (CollectionUtils.isNotEmpty(userTypes)) {
        w.in(UserEntity::getUserType, userTypes);
      }
      if (StringUtils.hasText(tenantId)) {
        w.eq(UserEntity::getTenantId, tenantId);
      } else {
        w.isNull(UserEntity::getTenantId);
      }
      w.eq(UserEntity::getEnabled, Boolean.TRUE);
    });

    List<SelectItem> selectItems = entities.stream().map(m ->
      SelectItem.create()
        .setId(m.getUserId())
        .setText(m.getDisplayName())
        .setCode(m.getUserName())
        .putAttr("mobile", m.getMobile())
    ).toList();
    return HttpResult.okBody(selectItems);
  }

}
