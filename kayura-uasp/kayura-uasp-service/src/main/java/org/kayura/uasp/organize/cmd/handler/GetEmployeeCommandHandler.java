/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
