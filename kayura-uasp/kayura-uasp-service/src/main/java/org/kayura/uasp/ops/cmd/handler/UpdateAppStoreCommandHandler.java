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
import org.kayura.uasp.applibrary.AppStorePayload;
import org.kayura.uasp.ops.cmd.UpdateAppStoreCommand;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateAppStoreCommandHandler implements CommandHandler<UpdateAppStoreCommand, HttpResult> {

  private final AppStoreManager appStoreManager;

  public UpdateAppStoreCommandHandler(AppStoreManager appStoreManager) {
    this.appStoreManager = appStoreManager;
  }

  @Transactional
  public HttpResult execute(UpdateAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AppStorePayload payload = command.getPayload();
    String releaseId = Optional.ofNullable(command.getReleaseId()).orElse(payload.getReleaseId());

    if (Boolean.TRUE.equals(payload.getReleased()) && StringUtils.isBlank(payload.getResourceId())) {
      return HttpResult.error("发布应用时必需提供下载的文件。");
    }

    AppStoreEntity entity = appStoreManager.selectById(releaseId);
    if (entity == null) {
      return HttpResult.error("更新的应用不存在。");
    }

    entity.setForced(payload.getForced());
    entity.setMode(payload.getMode());
    entity.setTitle(payload.getTitle());
    entity.setDescription(payload.getDescription());
    entity.setResourceId(payload.getResourceId());
    entity.setReleased(payload.getReleased());
    if (Boolean.TRUE.equals(payload.getReleased())) {
      entity.setReleaseTime(LocalDateTime.now());
    }
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    appStoreManager.updateById(releaseId, entity);

    return HttpResult.ok();
  }

}
