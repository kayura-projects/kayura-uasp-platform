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
