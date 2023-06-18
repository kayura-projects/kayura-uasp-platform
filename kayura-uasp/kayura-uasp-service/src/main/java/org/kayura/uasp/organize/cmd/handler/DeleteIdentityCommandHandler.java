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
import org.kayura.uasp.organize.cmd.DeleteIdentityCommand;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class DeleteIdentityCommandHandler implements CommandHandler<DeleteIdentityCommand, HttpResult> {

  private final IdentityManager identityManager;

  public DeleteIdentityCommandHandler(IdentityManager identityManager) {
    this.identityManager = identityManager;
  }

  @Transactional
  public HttpResult execute(DeleteIdentityCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String identityId = command.getIdentityId();
    Set<String> identityIds = command.getIdentityIds();

    if (StringUtils.hasText(identityId)) {
      identityManager.deleteById(identityId);
    } else if (CollectionUtils.isNotEmpty(identityIds)) {
      identityManager.deleteByIds(identityIds);
    }

    return HttpResult.ok();
  }
}
