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

package org.kayura.uasp.workflow.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

import java.time.LocalDateTime;

/**
 * 流转标签(uasp_flow_label) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_flow_label")
public class FlowLabelEntity {

  @Id
  private String businessKey;
  private String labelValue;
  private LocalDateTime updateTime;

  public static FlowLabelEntity create() {
    return new FlowLabelEntity();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public FlowLabelEntity setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getLabelValue() {
    return labelValue;
  }

  public FlowLabelEntity setLabelValue(String labelValue) {
    this.labelValue = labelValue;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public FlowLabelEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}