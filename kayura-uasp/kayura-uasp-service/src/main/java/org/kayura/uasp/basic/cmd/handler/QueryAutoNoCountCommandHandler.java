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
import org.kayura.type.HttpResult;
import org.kayura.uasp.autono.AutoNoCountVo;
import org.kayura.uasp.basic.cmd.QueryAutoNoCountCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.entity.AutoNoCountEntity;
import org.kayura.uasp.basic.entity.AutoNoRecycleEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.kayura.uasp.basic.manage.AutoNoCountManager;
import org.kayura.uasp.basic.manage.AutoNoRecycleManager;
import org.kayura.uasp.utils.UaspConsts;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QueryAutoNoCountCommandHandler implements CommandHandler<QueryAutoNoCountCommand, HttpResult> {

  private final AutoNoConfigManager configManager;
  private final AutoNoCountManager countManager;
  private final AutoNoRecycleManager recycleManager;

  public QueryAutoNoCountCommandHandler(AutoNoConfigManager configManager,
                                        AutoNoCountManager countManager,
                                        AutoNoRecycleManager recycleManager) {
    this.configManager = configManager;
    this.countManager = countManager;
    this.recycleManager = recycleManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryAutoNoCountCommand command) {

    String configId = command.getConfigId();
    String tenantId = command.getTenantId();

    List<AutoNoCountVo> counts;
    AutoNoConfigEntity entity = configManager.selectById(configId);
    if (entity != null) {
      counts = this.selectCounts(entity, tenantId);
    } else {
      counts = new ArrayList<>();
    }

    return HttpResult.okBody(counts);
  }

  protected List<AutoNoCountVo> selectCounts(AutoNoConfigEntity entity, String tenantId) {

    List<AutoNoCountVo> counts = null;
    List<AutoNoCountEntity> countEntities = countManager.selectList(w -> {
      w.eq(AutoNoCountEntity::getDefineId, entity.getDefineId());
      if (UaspConsts.GLOBAL.equalsIgnoreCase(tenantId)) {
        w.isNull(AutoNoCountEntity::getTenantId);
      } else {
        w.eq(AutoNoCountEntity::getTenantId, tenantId);
      }
    });
    if (!countEntities.isEmpty()) {
      counts = countEntities.stream().map(m ->
        AutoNoCountVo.create()
          .setCountId(m.getCountId())
          .setCycleValue(m.getCycleValue())
          .setCountValue(m.getCountValue())
      ).toList();
      List<String> countIds = counts.stream().map(AutoNoCountVo::getCountId).toList();
      List<AutoNoRecycleEntity> recycles = recycleManager.selectList(w ->
        w.in(AutoNoRecycleEntity::getCountId, countIds)
      );
      counts.forEach(e -> e.setRecycleNos(
        recycles.stream().filter(x -> e.getCountId().equals(x.getCountId()))
          .map(AutoNoRecycleEntity::getRecycleNo).toList()
      ));
    }
    return Optional.ofNullable(counts).orElse(new ArrayList<>());
  }

}
