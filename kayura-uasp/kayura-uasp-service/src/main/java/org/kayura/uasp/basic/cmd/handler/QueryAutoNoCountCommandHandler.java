/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
import org.kayura.uasp.utils.UaspConstants;
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
      if (UaspConstants.GLOBAL.equalsIgnoreCase(tenantId)) {
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
