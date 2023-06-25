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
