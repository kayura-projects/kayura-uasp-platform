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

package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.ops.cmd.DeleteAppStoreCommand;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteAppStoreCommandHandler implements CommandHandler<DeleteAppStoreCommand, HttpResult> {

  private final AppStoreManager appStoreManager;

  public DeleteAppStoreCommandHandler(AppStoreManager appStoreManager) {
    this.appStoreManager = appStoreManager;
  }

  @Transactional
  public HttpResult execute(DeleteAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String releaseId = Optional.ofNullable(command.getReleaseId()).orElse(payload.getId());

    if (StringUtils.hasText(releaseId)) {
      appStoreManager.deleteById(releaseId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      appStoreManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
