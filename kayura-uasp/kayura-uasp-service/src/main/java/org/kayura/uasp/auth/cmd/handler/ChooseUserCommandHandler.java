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
