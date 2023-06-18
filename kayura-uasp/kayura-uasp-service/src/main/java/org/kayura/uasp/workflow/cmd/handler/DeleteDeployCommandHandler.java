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
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.workflow.cmd.DeleteDeployCommand;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteDeployCommandHandler implements CommandHandler<DeleteDeployCommand, HttpResult> {

  private final RepositoryService repositoryService;

  public DeleteDeployCommandHandler(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Transactional
  public HttpResult execute(DeleteDeployCommand command) {

    LoginUser loginUser = command.getLoginUser();
    boolean cascade = command.isCascade();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String deployId = Optional.ofNullable(command.getDeployId()).orElse(payload.getId());

    if (StringUtils.hasText(deployId)) {
      repositoryService.deleteDeployment(deployId, cascade);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      for (String id : payload.getIds()) {
        repositoryService.deleteDeployment(id, cascade);
      }
    }

    return HttpResult.ok();
  }
}
