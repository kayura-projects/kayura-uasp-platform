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

package org.kayura.uasp.team;

import org.kayura.type.DataStatus;
import org.kayura.vaildation.Create;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TeamBody {

  @NotBlank(groups = Create.class)
  private String tenantId;
  private String code;
  @NotBlank
  private String name;
  private Integer sort;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private DataStatus status;
  private String remark;

  public String getTenantId() {
    return tenantId;
  }

  public TeamBody setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public TeamBody setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public TeamBody setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public TeamBody setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public TeamBody setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public TeamBody setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public TeamBody setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TeamBody setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
