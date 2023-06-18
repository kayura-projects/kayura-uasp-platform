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
import org.kayura.except.ExceptUtils;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.PositionPayload;
import org.kayura.uasp.organize.PositionVo;
import org.kayura.uasp.organize.cmd.CreatePositionCommand;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreatePositionCommandHandler implements CommandHandler<CreatePositionCommand, HttpResult> {

  private final DepartManager departManager;
  private final PositionManager positionManager;
  private final ModelMapper modelMapper;

  public CreatePositionCommandHandler(DepartManager departManager,
                                      PositionManager positionManager,
                                      ModelMapper modelMapper) {
    this.departManager = departManager;
    this.positionManager = positionManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreatePositionCommand command) {

    LoginUser loginUser = command.getLoginUser();
    PositionPayload payload = command.getPayload();

    if (departManager.selectCount(w -> w.eq(DepartEntity::getDepartId, payload.getDepartId())) == 0) {
      ExceptUtils.business("指定的部门ID不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) && positionManager.selectCount(w -> {
      w.eq(PositionEntity::getDepartId, payload.getDepartId());
      w.eq(PositionEntity::getCode, payload.getCode());
    }) > 0) {
      ExceptUtils.business("编号已经存在，不允许重复。");
    }

    PositionEntity entity = PositionEntity.create();
    entity.setPositionId(positionManager.nextId());
    entity.setDepartId(payload.getDepartId());
    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setLevel(payload.getLevel() != null ? payload.getLevel() : 0);
    entity.setRemark(payload.getRemark());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(DateUtils.now());
    entity.setSort(payload.getSort() != null ? payload.getSort() : 0);
    entity.setStatus(payload.getStatus());
    positionManager.insertOne(entity);

    PositionVo model = modelMapper.map(entity, PositionVo.class);
    return HttpResult.okBody(model);
  }
}
