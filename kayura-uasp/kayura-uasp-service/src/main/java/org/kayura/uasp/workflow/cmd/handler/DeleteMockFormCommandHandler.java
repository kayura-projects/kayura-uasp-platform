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
import org.kayura.uasp.activiti.manage.ActivitiManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.workflow.cmd.DeleteMockFormCommand;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteMockFormCommandHandler implements CommandHandler<DeleteMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ActivitiManager activitiManager;

  public DeleteMockFormCommandHandler(MockFormManager mockFormManager,
                                      ActivitiManager activitiManager) {
    this.mockFormManager = mockFormManager;
    this.activitiManager = activitiManager;
  }

  @Transactional
  public HttpResult execute(DeleteMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String mockId = Optional.ofNullable(command.getMockId()).orElse(payload.getId());

    List<MockFormEntity> entities = null;
    if (StringUtils.hasText(mockId)) {
      entities = mockFormManager.selectList(w -> w.eq(MockFormEntity::getMockId, mockId));
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      entities = mockFormManager.selectList(w -> w.in(MockFormEntity::getMockId, payload.getIds()));
    }

    // 如果启动了流程，还需要将流程一并删除.
    if (entities != null) {
      for (MockFormEntity entity : entities) {
        if (!ApproveStatus.Draft.equals(entity.getStatus())) {
          activitiManager.deleteProcessByBusinessKey(entity.getMockId());
        }
        mockFormManager.deleteById(entity.getMockId());
      }
    }

    return HttpResult.ok();
  }
}
