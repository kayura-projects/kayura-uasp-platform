/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
