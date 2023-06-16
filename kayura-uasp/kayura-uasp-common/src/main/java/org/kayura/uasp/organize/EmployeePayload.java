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

import org.kayura.type.UserTypes;
import org.kayura.vaildation.CodeValid;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeePayload {

  @NotBlank(groups = Update.class)
  private String employeeId;
  private String tenantId;
  private String companyId;
  private String departId;
  private String positionId;
  @NotBlank
  @CodeValid
  private String jobNo;
  @NotBlank
  private String realName;
  private LocalDate birthday;
  private String nation;
  private String origin;
  private SexEnum sex;
  private String education;
  private Double height;
  private Double weight;
  private String marital;
  private String idCard;
  private String address;
  private LocalDateTime enterDate;
  private LocalDateTime overDate;
  private WorkStatus status;
  private String remark;

  @NotBlank
  private String userName;
  @NotBlank
  private String displayName;
  private String mobile;
  private String email;
  private UserTypes userType;
  private String avatar;
  private String password;
  private LocalDate accountExpire;
  private LocalDate passwordExpire;
  private Boolean enabled;
  private Boolean locked;

  public String getEmployeeId() {
    return employeeId;
  }

  public EmployeePayload setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public EmployeePayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getJobNo() {
    return jobNo;
  }

  public EmployeePayload setJobNo(String jobNo) {
    this.jobNo = jobNo;
    return this;
  }

  public String getRealName() {
    return realName;
  }

  public EmployeePayload setRealName(String realName) {
    this.realName = realName;
    return this;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public EmployeePayload setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public String getNation() {
    return nation;
  }

  public EmployeePayload setNation(String nation) {
    this.nation = nation;
    return this;
  }

  public String getOrigin() {
    return origin;
  }

  public EmployeePayload setOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  public SexEnum getSex() {
    return sex;
  }

  public EmployeePayload setSex(SexEnum sex) {
    this.sex = sex;
    return this;
  }

  public String getEducation() {
    return education;
  }

  public EmployeePayload setEducation(String education) {
    this.education = education;
    return this;
  }

  public Double getHeight() {
    return height;
  }

  public EmployeePayload setHeight(Double height) {
    this.height = height;
    return this;
  }

  public Double getWeight() {
    return weight;
  }

  public EmployeePayload setWeight(Double weight) {
    this.weight = weight;
    return this;
  }

  public String getMarital() {
    return marital;
  }

  public EmployeePayload setMarital(String marital) {
    this.marital = marital;
    return this;
  }

  public String getIdCard() {
    return idCard;
  }

  public EmployeePayload setIdCard(String idCard) {
    this.idCard = idCard;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public EmployeePayload setAddress(String address) {
    this.address = address;
    return this;
  }

  public LocalDateTime getEnterDate() {
    return enterDate;
  }

  public EmployeePayload setEnterDate(LocalDateTime enterDate) {
    this.enterDate = enterDate;
    return this;
  }

  public LocalDateTime getOverDate() {
    return overDate;
  }

  public EmployeePayload setOverDate(LocalDateTime overDate) {
    this.overDate = overDate;
    return this;
  }

  public WorkStatus getStatus() {
    return status;
  }

  public EmployeePayload setStatus(WorkStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public EmployeePayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public EmployeePayload setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public EmployeePayload setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public EmployeePayload setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public EmployeePayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public EmployeePayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public EmployeePayload setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public EmployeePayload setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public EmployeePayload setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getPositionId() {
    return positionId;
  }

  public EmployeePayload setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public EmployeePayload setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public EmployeePayload setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public EmployeePayload setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public EmployeePayload setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public LocalDate getPasswordExpire() {
    return passwordExpire;
  }

  public EmployeePayload setPasswordExpire(LocalDate passwordExpire) {
    this.passwordExpire = passwordExpire;
    return this;
  }
}
