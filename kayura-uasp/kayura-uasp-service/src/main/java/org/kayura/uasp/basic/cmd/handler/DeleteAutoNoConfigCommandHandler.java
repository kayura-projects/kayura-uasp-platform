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
import org.kayura.uasp.basic.cmd.DeleteAutoNoConfigCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.kayura.uasp.basic.manage.AutoNoDefineManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class DeleteAutoNoConfigCommandHandler implements CommandHandler<DeleteAutoNoConfigCommand, HttpResult> {

  private final AutoNoConfigManager configManager;
  private final AutoNoDefineManager defineManager;

  public DeleteAutoNoConfigCommandHandler(AutoNoConfigManager configManager,
                                          AutoNoDefineManager defineManager) {
    this.configManager = configManager;
    this.defineManager = defineManager;
  }

  @Transactional
  public HttpResult execute(DeleteAutoNoConfigCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String configId = Optional.ofNullable(command.getConfigId()).orElse(payload.getId());

    List<AutoNoConfigEntity> entities = configManager.selectList(w -> {
      if (StringUtils.hasText(configId)) {
        w.eq(AutoNoConfigEntity::getConfigId, configId);
      } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(AutoNoConfigEntity::getConfigId, payload.getIds());
      }
    });

    if (CollectionUtils.isNotEmpty(entities)) {
      for (AutoNoConfigEntity entity : entities) {
        if (loginUser.hasRootOrAdmin()) {
          if (UaspConstants.GLOBAL.equalsIgnoreCase(entity.getTenantId())) {
            defineManager.deleteById(entity.getDefineId());
          } else {
            configManager.deleteById(entity.getConfigId());
          }
        } else if (Objects.equals(loginUser.getTenantId(), entity.getTenantId())) {
          configManager.deleteById(entity.getConfigId());
        }
      }
    }

    return HttpResult.ok();
  }

}
