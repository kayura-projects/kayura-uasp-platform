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
import org.kayura.type.UsableStatus;
import org.kayura.uasp.organize.cmd.CreateCompanyApplicCommand;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.company.CompanyApplicPayload;
import org.kayura.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CreateCompanyApplicCommandHandler implements CommandHandler<CreateCompanyApplicCommand, HttpResult> {

  private final CompanyApplicManager companyApplicManager;

  public CreateCompanyApplicCommandHandler(CompanyApplicManager companyApplicManager) {
    this.companyApplicManager = companyApplicManager;
  }

  @Transactional
  public HttpResult execute(CreateCompanyApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyApplicPayload payload = command.getPayload();
    List<CompanyApplicPayload> payloads = Optional.ofNullable(command.getPayloads())
      .orElse(payload != null ? Collections.singletonList(payload) : new ArrayList<>());

    for (CompanyApplicPayload row : payloads) {
      if (companyApplicManager.selectCount(w -> {
        w.eq(CompanyApplicEntity::getCompanyId, row.getCompanyId());
        w.eq(CompanyApplicEntity::getAppId, row.getAppId());
      }) == 0) {
        CompanyApplicEntity entity = CompanyApplicEntity.create();
        entity.setId(companyApplicManager.nextId());
        entity.setCompanyId(row.getCompanyId());
        entity.setAppId(row.getAppId());
        entity.setCreateTime(DateUtils.now());
        entity.setCreatorId(loginUser.getUserId());
        entity.setExpireTime(row.getExpireTime());
        entity.setStatus(Optional.ofNullable(row.getStatus()).orElse(UsableStatus.Valid));
        companyApplicManager.insertOne(entity);
      }
    }

    return HttpResult.ok();
  }

}
