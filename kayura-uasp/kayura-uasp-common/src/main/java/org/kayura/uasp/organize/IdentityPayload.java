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
