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
