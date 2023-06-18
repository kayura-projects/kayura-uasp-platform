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

package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.autono.NewNoArgs;
import org.kayura.uasp.basic.cmd.CalcNewNoCommand;
import org.kayura.uasp.basic.manage.AutoNoManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CalcNewNoCommandHandler implements CommandHandler<CalcNewNoCommand, HttpResult> {

  private final AutoNoManager autoNoManager;

  public CalcNewNoCommandHandler(AutoNoManager autoNoManager) {
    this.autoNoManager = autoNoManager;
  }

  @Transactional
  public HttpResult execute(CalcNewNoCommand command) {

    LoginUser loginUser = command.getLoginUser();
    NewNoArgs args = command.getArgs();
    boolean fixed = command.isFixed();

    if (loginUser.hasTenantUser()) {
      args.setTenantId(loginUser.getTenantId());
    }
    if (StringUtils.isBlank(args.getTenantId())) {
      return HttpResult.error("必需要指定租户ID。");
    }

    String newNo = autoNoManager.calcNewNo(args, fixed);
    return HttpResult.okBody(newNo);
  }

}
