/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
