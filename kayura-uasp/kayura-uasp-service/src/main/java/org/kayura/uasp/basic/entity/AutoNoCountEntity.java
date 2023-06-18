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