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
