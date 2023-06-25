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
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.SexEnum;
import org.kayura.uasp.organize.WorkStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工(uasp_employee) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_employee")
public class EmployeeEntity {

  /** 员工ID */
  @Id
  @ForeignKey(entity = UserEntity.class, alias = "eu")
  private String employeeId;
  @RefColumn(from = "eu")
  private String userName;
  @RefColumn(from = "eu")
  private String displayName;
  @RefColumn(from = "eu")
  private String mobile;
  @RefColumn(from = "eu")
  private UserTypes userType;
  /** 租户ID */
  private String tenantId;
  /** 公司ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "ee")
  private String companyId;
  @RefColumn(from = "ee", value = "short_name_")
  private String companyName;
  @RefColumn(from = "ee", value = "type_")
  private CompanyTypes companyType;
  /** 部门ID */
  @ForeignKey(entity = DepartEntity.class, alias = "dep")
  private String departId;
  @RefColumn(from = "dep", value = "name_")
  private String departName;
  /** 工号 */
  private String jobNo;
  /** 真实姓名 */
  private String realName;
  /** 出生日期 */
  private LocalDate birthday;
  /** 籍贯 */
  private String origin;
  /** 民族 */
  private String nation;
  /** 性别:M男,W女,N未指明; */
  private SexEnum sex;
  /** 学历 */
  private String education;
  /** 身高 */
  private Double height;
  /** 体重 */
  private Double weight;
  /** 婚姻状况 */
  private String marital;
  /** 身份证号 */
  private String idCard;
  /** 常住地址 */
  private String address;
  /** 入职日期 */
  private LocalDateTime enterDate;
  /** 离职日期 */
  private LocalDateTime overDate;
  /** 创建人ID */
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;
  /** 修改时间 */
  private LocalDateTime updateTime;
  /** 工作状态:Active在职,Suspend停职,Leave离职; */
  private WorkStatus status;
  /** 备注 */
  private String remark;

  public static EmployeeEntity create() {
    return new EmployeeEntity();
  }

  public String getCreatorName() {
    return creatorName;
  }

  public EmployeeEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public EmployeeEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public EmployeeEntity setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public EmployeeEntity setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getJobNo() {
    return jobNo;
  }

  public EmployeeEntity setJobNo(String jobNo) {
    this.jobNo = jobNo;
    return this;
  }

  public String getRealName() {
    return realName;
  }

  public EmployeeEntity setRealName(String realName) {
    this.realName = realName;
    return this;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public EmployeeEntity setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public String getNation() {
    return nation;
  }

  public EmployeeEntity setNation(String nation) {
    this.nation = nation;
    return this;
  }

  public String getOrigin() {
    return origin;
  }

  public EmployeeEntity setOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  public SexEnum getSex() {
    return sex;
  }

  public EmployeeEntity setSex(SexEnum sex) {
    this.sex = sex;
    return this;
  }

  public String getEducation() {
    return education;
  }

  public EmployeeEntity setEducation(String education) {
    this.education = education;
    return this;
  }

  public Double getHeight() {
    return height;
  }

  public EmployeeEntity setHeight(Double height) {
    this.height = height;
    return this;
  }

  public Double getWeight() {
    return weight;
  }

  public EmployeeEntity setWeight(Double weight) {
    this.weight = weight;
    return this;
  }

  public String getMarital() {
    return marital;
  }

  public EmployeeEntity setMarital(String marital) {
    this.marital = marital;
    return this;
  }

  public String getIdCard() {
    return idCard;
  }

  public EmployeeEntity setIdCard(String idCard) {
    this.idCard = idCard;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public EmployeeEntity setAddress(String address) {
    this.address = address;
    return this;
  }

  public LocalDateTime getEnterDate() {
    return enterDate;
  }

  public EmployeeEntity setEnterDate(LocalDateTime enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDateTime getOverDate() {
    return overDate;
  }

  public EmployeeEntity setOverDate(LocalDateTime overDate) {
    this.overDate = overDate;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public EmployeeEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public EmployeeEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public EmployeeEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public EmployeeEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public WorkStatus getStatus() {
    return status;
  }

  public EmployeeEntity setStatus(WorkStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public EmployeeEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public EmployeeEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public EmployeeEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public CompanyTypes getCompanyType() {
    return companyType;
  }

  public EmployeeEntity setCompanyType(CompanyTypes companyType) {
    this.companyType = companyType;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public EmployeeEntity setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public EmployeeEntity setDepartName(String departName) {
    this.departName = departName;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public EmployeeEntity setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public EmployeeEntity setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public EmployeeEntity setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public EmployeeEntity setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }
}