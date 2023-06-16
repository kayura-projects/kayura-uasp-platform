/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.workflow.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.workflow.entity.FlowLabelEntity;
import org.kayura.uasp.workflow.mapper.FlowLabelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FlowLabelManager extends CrudManagerImpl<FlowLabelMapper, FlowLabelEntity> {

  protected FlowLabelManager(FlowLabelMapper baseMapper) {
    super(baseMapper);
  }

  public String readFlowLabel(String businessKey) {

    FlowLabelEntity entity = this.selectById(businessKey);
    return entity != null ? entity.getLabelValue() : null;
  }

  public void insertOrUpdate(String businessKey, String labelValue) {

    FlowLabelEntity entity = this.selectById(businessKey);
    if (entity != null) {
      this.updateByWhere(w -> {
        w.set(FlowLabelEntity::getLabelValue, labelValue);
        w.set(FlowLabelEntity::getUpdateTime, LocalDateTime.now());
        w.eq(FlowLabelEntity::getBusinessKey, businessKey);
      });
    } else {
      this.insertOne(
        FlowLabelEntity.create()
          .setBusinessKey(businessKey)
          .setLabelValue(labelValue)
          .setUpdateTime(LocalDateTime.now())
      );
    }
  }

}
