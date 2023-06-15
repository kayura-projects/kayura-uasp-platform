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
