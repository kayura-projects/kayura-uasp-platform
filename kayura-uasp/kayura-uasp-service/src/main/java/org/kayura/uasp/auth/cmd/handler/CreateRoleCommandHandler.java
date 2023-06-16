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
