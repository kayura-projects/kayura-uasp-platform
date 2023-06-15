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
