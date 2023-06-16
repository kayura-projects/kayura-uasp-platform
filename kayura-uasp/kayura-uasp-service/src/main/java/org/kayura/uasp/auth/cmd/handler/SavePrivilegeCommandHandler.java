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
import org.kayura.uasp.auth.cmd.SavePrivilegeCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.manage.PrivilegeManager;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.privilege.ModuleAction;
import org.kayura.uasp.privilege.PrivilegeBody;
import org.kayura.uasp.privilege.PrivilegeTypes;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SavePrivilegeCommandHandler implements CommandHandler<SavePrivilegeCommand, HttpResult> {

  private final PrivilegeManager privilegeManager;
  private final RoleManager roleManager;

  public SavePrivilegeCommandHandler(PrivilegeManager privilegeManager,
                                     RoleManager roleManager) {
    this.privilegeManager = privilegeManager;
    this.roleManager = roleManager;
  }

  @Transactional
  public HttpResult execute(SavePrivilegeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String appId = command.getAppId();
    PrivilegeTypes type = command.getType();
    String linkId = command.getLinkId();
    List<ModuleAction> privileges = command.getPrivileges();

    // 若未指定 appId，将从 linkId 从提取.
    if (StringUtils.isBlank(appId)) {
      if (PrivilegeTypes.Role.equals(type)) {
        RoleEntity role = roleManager.selectOne(w -> {
          w.select(RoleEntity::getAppId);
          w.select(RoleEntity::getTenantId);
          w.eq(RoleEntity::getRoleId, linkId);
        });
        if (role == null) {
          return HttpResult.error("未指定所属应用ID。");
        }
        appId = role.getAppId();
      }
    }

    privilegeManager.savePrivileges(
      PrivilegeBody.create()
        .setAppId(appId)
        .setLinkId(linkId)
        .setType(type)
        .setPrivileges(privileges)
    );

    return HttpResult.ok();
  }

}
