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
import org.kayura.uasp.organize.IdentityPayload;
import org.kayura.uasp.organize.IdentityVo;
import org.kayura.uasp.organize.JobStatus;
import org.kayura.uasp.organize.cmd.CreateIdentityCommand;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateIdentityCommandHandler implements CommandHandler<CreateIdentityCommand, HttpResult> {

  private final IdentityManager identityManager;
  private final PositionManager positionManager;
  private final ModelMapper modelMapper;

  public CreateIdentityCommandHandler(IdentityManager identityManager,
                                      PositionManager positionManager,
                                      ModelMapper modelMapper) {
    this.identityManager = identityManager;
    this.positionManager = positionManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateIdentityCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdentityPayload payload = command.getPayload();

    if (StringUtils.isAllBlank(payload.getDepartId(), payload.getPositionId())) {
      return HttpResult.error("必需指定部门或者岗位。");
    }

    if (StringUtils.isBlank(payload.getDepartId())) {
      PositionEntity position = positionManager.selectOne(w -> {
        w.select(PositionEntity::getDepartId);
        w.eq(PositionEntity::getPositionId, payload.getPositionId());
      });
      if (position == null) {
        return HttpResult.error("指定的岗位不存在。");
      }
      payload.setDepartId(position.getDepartId());
    }

    payload.setStatus(Optional.ofNullable(payload.getStatus()).orElse(JobStatus.Active));

    IdentityEntity entity = IdentityEntity.create()
      .setIdentityId(identityManager.nextId())
      .setDepartId(payload.getDepartId())
      .setPositionId(payload.getPositionId())
      .setEmployeeId(payload.getEmployeeId())
      .setEnterDate(payload.getEnterDate())
      .setOverDate(payload.getOverDate())
      .setStatus(payload.getStatus())
      .setRemark(payload.getRemark());
    identityManager.insertOne(entity);

    IdentityVo model = modelMapper.map(entity, IdentityVo.class);
    return HttpResult.okBody(model);
  }

}
