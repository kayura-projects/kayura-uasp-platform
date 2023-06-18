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

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applic.ApplicPayload;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.dev.cmd.CreateApplicCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateApplicCommandHandler implements CommandHandler<CreateApplicCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final ApplicManager applicManager;

  public CreateApplicCommandHandler(ModelMapper modelMapper,
                                    ApplicManager applicManager) {
    this.modelMapper = modelMapper;
    this.applicManager = applicManager;
  }

  @Transactional
  public HttpResult execute(CreateApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ApplicPayload payload = command.getPayload();

    if (UaspConstants.UASP_APP_CODE.equalsIgnoreCase(payload.getCode())) {
      return HttpResult.error("UASP 为保留应用编号，不可使用。");
    }

    if (applicManager.selectCount(w -> w.eq(ApplicEntity::getCode, payload.getCode())) > 0) {
      return HttpResult.error("应用编码已经被占用。");
    }

    if (applicManager.selectCount(w -> w.eq(ApplicEntity::getName, payload.getName())) > 0) {
      return HttpResult.error("应用名称已经被占用。");
    }

    ApplicEntity entity = ApplicEntity.create()
      .setAppId(applicManager.nextId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setLevel(payload.getLevel())
      .setType(payload.getType())
      .setUrl(payload.getUrl())
      .setSort(payload.getSort())
      .setNeedRelease(payload.getNeedRelease())
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setStatus(payload.getStatus())
      .setRemark(payload.getRemark());
    applicManager.insertOne(entity);

    ApplicVo model = modelMapper.map(entity, ApplicVo.class);
    return HttpResult.okBody(model);
  }

}
