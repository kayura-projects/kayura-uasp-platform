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
