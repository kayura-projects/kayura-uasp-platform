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
import org.kayura.uasp.basic.cmd.DeleteAutoNoCountCommand;
import org.kayura.uasp.basic.manage.AutoNoCountManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteAutoNoCountCommandHandler implements CommandHandler<DeleteAutoNoCountCommand, HttpResult> {

  private final AutoNoCountManager countManager;

  public DeleteAutoNoCountCommandHandler(AutoNoCountManager countManager) {
    this.countManager = countManager;
  }

  @Transactional
  public HttpResult execute(DeleteAutoNoCountCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String countId = Optional.ofNullable(command.getCountId()).orElse(payload.getId());

    if (StringUtils.hasText(countId)) {
      countManager.deleteById(countId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      countManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
