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
