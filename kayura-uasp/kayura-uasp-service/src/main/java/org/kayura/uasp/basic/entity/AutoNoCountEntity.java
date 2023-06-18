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

package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

/**
 * 计数周期(uasp_auto_no_count) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_auto_no_count")
public class AutoNoCountEntity {

  /** 计数周期 */
  @Id
  private String countId;
  /** 定义ID */
  private String defineId;
  /** 租户ID */
  private String tenantId;
  /** 周期 */
  private String cycleValue;
  /** 计数 */
  private Integer countValue;

  public static AutoNoCountEntity create() {
    return new AutoNoCountEntity();
  }

  public String getCountId() {
    return countId;
  }

  public AutoNoCountEntity setCountId(String countId) {
    this.countId = countId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public AutoNoCountEntity setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getCycleValue() {
    return cycleValue;
  }

  public AutoNoCountEntity setCycleValue(String cycleValue) {
    this.cycleValue = cycleValue;
    return this;
  }

  public Integer getCountValue() {
    return countValue;
  }

  public AutoNoCountEntity setCountValue(Integer countValue) {
    this.countValue = countValue;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AutoNoCountEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}