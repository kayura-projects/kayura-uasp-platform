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
 * 审批常用语(uasp_used_opinion) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_used_opinion")
public class UsedOpinionEntity {

  @Id
  private String opinionId;
  private String userId;
  private String content;
  private Integer useCount;
  private LocalDateTime useTime;

  public static UsedOpinionEntity create() {
    return new UsedOpinionEntity();
  }

  public String getOpinionId() {
    return opinionId;
  }

  public UsedOpinionEntity setOpinionId(String opinionId) {
    this.opinionId = opinionId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public UsedOpinionEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public UsedOpinionEntity setContent(String content) {
    this.content = content;
    return this;
  }

  public Integer getUseCount() {
    return useCount;
  }

  public UsedOpinionEntity setUseCount(Integer useCount) {
    this.useCount = useCount;
    return this;
  }

  public LocalDateTime getUseTime() {
    return useTime;
  }

  public UsedOpinionEntity setUseTime(LocalDateTime useTime) {
    this.useTime = useTime;
    return this;
  }
}