package org.kayura.uasp.organize;

import java.time.LocalDate;

public class IdentityVo {

  private String identityId;
  private String employeeId;
  private String departId;
  private String departName;
  private String positionId;
  private String positionName;
  private LocalDate enterDate;
  private LocalDate overDate;
  private JobStatus status;
  private String remark;

  public String getIdentityId() {
    return identityId;
  }

  public IdentityVo setIdentityId(String identityId) {
    this.identityId = identityId;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public IdentityVo setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public IdentityVo setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public IdentityVo setDepartName(String departName) {
    this.departName = departName;
    return this;
  }

  public String getPositionId() {
    return positionId;
  }

  public IdentityVo setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public String getPositionName() {
    return positionName;
  }

  public IdentityVo setPositionName(String positionName) {
    this.positionName = positionName;
    return this;
  }

  public LocalDate getEnterDate() {
    return enterDate;
  }

  public IdentityVo setEnterDate(LocalDate enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDate getOverDate() {
    return overDate;
  }

  public IdentityVo setOverDate(LocalDate overDate) {
    this.overDate = overDate;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public IdentityVo setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public IdentityVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
