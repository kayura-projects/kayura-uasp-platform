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
import org.kayura.uasp.auth.cmd.CreateRoleCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.role.RolePayload;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateRoleCommandHandler implements CommandHandler<CreateRoleCommand, HttpResult> {

  private final RoleManager roleManager;

  public CreateRoleCommandHandler(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  @Transactional
  public HttpResult execute(CreateRoleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    RolePayload payload = command.getPayload();
    RoleTypes roleType = command.getRoleType();

    if (StringUtils.hasText(loginUser.getTenantId())) {
      if (!StringUtils.isAllBlank(payload.getTenantId(), payload.getAppId())) {
        return HttpResult.error("公司人员创建角色时，禁止指定应用或租户。");
      }
      payload.setAppId(loginUser.getAppId());
      payload.setTenantId(loginUser.getTenantId());
    } else {
      if (StringUtils.isAllBlank(payload.getTenantId(), payload.getAppId())) {
        return HttpResult.error("后台管理员创建角色时，必需指定应用或租户。");
      }
    }

    if (StringUtils.isNotBlank(payload.getCode())) {
      if (roleManager.selectCount(w -> w.eq(RoleEntity::getCode, payload.getCode())) > 0) {
        return HttpResult.error("编号已经被占用。");
      }
    }

    RoleEntity entity = RoleEntity.create();
    entity.setRoleId(roleManager.nextId());
    entity.setAppId(payload.getAppId());
    entity.setTenantId(payload.getTenantId());
    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setType(roleType);
    entity.setSort(payload.getSort());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(DateUtils.now());
    entity.setStatus(payload.getStatus());
    roleManager.insertOne(entity);

    return HttpResult.ok();
  }

}
