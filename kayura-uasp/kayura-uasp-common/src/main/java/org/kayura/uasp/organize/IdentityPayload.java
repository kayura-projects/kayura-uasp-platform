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

package org.kayura.uasp.organize;

import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class IdentityPayload {

  @NotBlank(groups = Update.class)
  private String identityId;
  @NotBlank(groups = Create.class)
  private String employeeId;
  private String departId;
  private String positionId;
  private LocalDate enterDate;
  private LocalDate overDate;
  private JobStatus status;
  private Boolean primary;
  private String remark;

  public String getEmployeeId() {
    return employeeId;
  }

  public IdentityPayload setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public IdentityPayload setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getPositionId() {
    return positionId;
  }

  public IdentityPayload setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public LocalDate getEnterDate() {
    return enterDate;
  }

  public IdentityPayload setEnterDate(LocalDate enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDate getOverDate() {
    return overDate;
  }

  public IdentityPayload setOverDate(LocalDate overDate) {
    this.overDate = overDate;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public IdentityPayload setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public IdentityPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getIdentityId() {
    return identityId;
  }

  public IdentityPayload setIdentityId(String identityId) {
    this.identityId = identityId;
    return this;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public IdentityPayload setPrimary(Boolean primary) {
    this.primary = primary;
    return this;
  }
}
