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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.ApproveStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.mockform.MockFormPayload;
import org.kayura.uasp.mockform.MockFormVo;
import org.kayura.uasp.workflow.cmd.CreateMockFormCommand;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class CreateMockFormCommandHandler implements CommandHandler<CreateMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ModelMapper modelMapper;

  public CreateMockFormCommandHandler(MockFormManager mockFormManager,
                                      ModelMapper modelMapper) {
    this.mockFormManager = mockFormManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MockFormPayload payload = command.getPayload();

    if (mockFormManager.selectCount(w ->
      w.eq(MockFormEntity::getCode, payload.getCode())
    ) > 0) {
      return HttpResult.error("编号 " + payload.getCode() + " 已经被占用。");
    }

    MockFormEntity entity = MockFormEntity.create()
      .setMockId(mockFormManager.nextId())
      .setUsage(payload.getUsage())
      .setTenantId(payload.getTenantId())
      .setFormId(payload.getFormId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setExtend(payload.getExtend())
      .setStatus(ApproveStatus.Draft)
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(LocalDateTime.now())
      .setRemark(payload.getRemark());
    mockFormManager.insertOne(entity);

    MockFormVo model = modelMapper.map(entity, MockFormVo.class);
    return HttpResult.okBody(model);
  }

}
