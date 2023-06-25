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