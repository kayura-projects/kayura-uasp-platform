/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserAvatarManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.organize.EmployeeVo;
import org.kayura.uasp.organize.cmd.GetEmployeeCommand;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.user.UserAvatarVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetEmployeeCommandHandler implements CommandHandler<GetEmployeeCommand, HttpResult> {

  private final EmployeeManager employeeManager;
  private final UserManager userManager;
  private final ModelMapper modelMapper;
  private final UserAvatarManager avatarManager;

  public GetEmployeeCommandHandler(EmployeeManager employeeManager,
                                   UserManager userManager,
                                   ModelMapper modelMapper,
                                   UserAvatarManager avatarManager) {
    this.employeeManager = employeeManager;
    this.userManager = userManager;
    this.modelMapper = modelMapper;
    this.avatarManager = avatarManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetEmployeeCommand command) {

    String employeeId = command.getEmployeeId();

    EmployeeVo model = null;
    EmployeeEntity employee = employeeManager.selectById(employeeId);
    if (employee != null) {
      model = modelMapper.map(employee, EmployeeVo.class);

      UserEntity user = userManager.selectById(employeeId);
      if (user != null) {
        model.setUserName(user.getUserName());
        model.setUserType(user.getUserType());
        model.setDisplayName(user.getDisplayName());
        model.setMobile(user.getMobile());
        model.setEmail(user.getEmail());
        model.setAvatar(user.getAvatar());
        model.setAccountExpire(user.getAccountExpire());
        model.setEnabled(user.getEnabled());
        model.setLocked(user.getLocked());

        // Avatar
        List<UserAvatarVo> avatars = avatarManager.queryHistory(employeeId);
        model.setHistoryAvatars(avatars);
      }
    }
    return HttpResult.okBody(model);
  }

}
