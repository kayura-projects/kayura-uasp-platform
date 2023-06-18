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

package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.ForeignKey;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.RefColumn;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.organize.JobStatus;

import java.time.LocalDate;

/**
 * 岗位身份(uasp_identity) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_identity")
public class IdentityEntity {

  /** 身份Id */
  @Id
  private String identityId;
  /** 部门ID */
  @ForeignKey(entity = DepartEntity.class, alias = "dpt")
  private String departId;
  @RefColumn(from = "dpt", value = "name_")
  private String departName;
  /** 岗位Id */
  @ForeignKey(entity = PositionEntity.class, alias = "pos")
  private String positionId;
  @RefColumn(from = "pos", value = "name_")
  private String positionName;
  /** 用户ID */
  @ForeignKey(entity = EmployeeEntity.class, alias = "emp")
  private String employeeId;
  @RefColumn(from = "emp", value = "real_name_")
  private String employeeName;
  /** 入岗日期 */
  private LocalDate enterDate;
  /** 离岗日期 */
  private LocalDate overDate;
  /** 岗位状态：A在岗,L离岗; */
  private JobStatus status;
  /** 备注 */
  private String remark;

  public static IdentityEntity create() {
    return new IdentityEntity();
  }

  public String getIdentityId() {
    return identityId;
  }

  public IdentityEntity setIdentityId(String identityId) {
    this.identityId = identityId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public IdentityEntity setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public IdentityEntity setDepartName(String departName) {
    this.departName = departName;
    return this;
  }

  public String getPositionId() {
    return positionId;
  }

  public IdentityEntity setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public String getPositionName() {
    return positionName;
  }

  public IdentityEntity setPositionName(String positionName) {
    this.positionName = positionName;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public IdentityEntity setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public IdentityEntity setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
    return this;
  }

  public LocalDate getEnterDate() {
    return enterDate;
  }

  public IdentityEntity setEnterDate(LocalDate enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDate getOverDate() {
    return overDate;
  }

  public IdentityEntity setOverDate(LocalDate overDate) {
    this.overDate = overDate;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public IdentityEntity setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public IdentityEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}