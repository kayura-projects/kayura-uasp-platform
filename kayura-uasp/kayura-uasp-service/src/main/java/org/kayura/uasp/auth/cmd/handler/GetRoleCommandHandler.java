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
import org.kayura.uasp.auth.cmd.GetRoleCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.role.RoleVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetRoleCommandHandler implements CommandHandler<GetRoleCommand, HttpResult> {

  private final RoleManager roleManager;
  private final ModelMapper modelMapper;

  public GetRoleCommandHandler(RoleManager roleManager,
                               ModelMapper modelMapper) {
    this.roleManager = roleManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetRoleCommand command) {

    String roleId = command.getRoleId();
    RoleEntity entity = roleManager.selectById(roleId);
    if (entity != null) {
      return HttpResult.okBody(modelMapper.map(entity, RoleVo.class));
    }
    return HttpResult.error("要查询的角色不存在。");
  }
}
