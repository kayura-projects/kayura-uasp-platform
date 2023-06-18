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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.DeleteOrganizeCommand;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteOrganizeCommandHandler implements CommandHandler<DeleteOrganizeCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final DepartManager departManager;
  private final PositionManager positionManager;

  public DeleteOrganizeCommandHandler(CompanyManager companyManager,
                                      DepartManager departManager,
                                      PositionManager positionManager) {
    this.companyManager = companyManager;
    this.departManager = departManager;
    this.positionManager = positionManager;
  }

  @Transactional
  public HttpResult execute(DeleteOrganizeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OrganizeTypes type = command.getType();
    IdPayload payload = Optional.ofNullable(command.getPayload())
      .orElse(IdPayload.create().setId(command.getOrgId()));

    Result result;
    if (OrganizeTypes.Company.equals(type)) {
      result = companyManager.deleteForPayload(payload);
    } else if (OrganizeTypes.Depart.equals(type)) {
      result = departManager.deleteForPayload(payload);
    } else {
      result = positionManager.deleteForPayload(payload);
    }
    return HttpResult.of(result);
  }


}
