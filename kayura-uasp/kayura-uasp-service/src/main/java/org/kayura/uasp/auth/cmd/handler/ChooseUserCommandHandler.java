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
