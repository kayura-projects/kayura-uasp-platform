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
