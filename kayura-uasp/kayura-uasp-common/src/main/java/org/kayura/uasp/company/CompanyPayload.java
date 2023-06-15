package org.kayura.uasp.company;

import org.kayura.type.DataStatus;
import org.kayura.uasp.organize.LeaderFrm;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CompanyPayload {

  @NotBlank(groups = Update.class)
  private String companyId;
  private String parentId;
  @NotBlank(groups = Create.class)
  private String tenantId;
  @NotNull(groups = Create.class)
  private CompanyTypes type;
  @NotBlank
  private String categoryId;
  @NotBlank
  private String code;
  @NotBlank
  private String shortName;
  private String fullName;
  private String contract;
  private String mobile;
  private String email;
  private String address;
  private String postCode;
  private String tel;
  private String fax;
  @NotNull
  private Integer sort;
  private String location;
  @NotNull
  private DataStatus status;
  private String remark;
  private List<LeaderFrm> leaders;

  public static CompanyPayload create() {
    return new CompanyPayload();
  }

  public String getParentId() {
    return parentId;
  }

  public CompanyPayload setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CompanyPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public CompanyTypes getType() {
    return type;
  }

  public CompanyPayload setType(CompanyTypes type) {
    this.type = type;
    return this;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public CompanyPayload setCategoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CompanyPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public CompanyPayload setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public CompanyPayload setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public CompanyPayload setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getPostCode() {
    return postCode;
  }

  public CompanyPayload setPostCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  public String getContract() {
    return contract;
  }

  public CompanyPayload setContract(String contract) {
    this.contract = contract;
    return this;
  }

  public String getTel() {
    return tel;
  }

  public CompanyPayload setTel(String tel) {
    this.tel = tel;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public CompanyPayload setEmail(String email) {
    this.email = email;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public CompanyPayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getLocation() {
    return location;
  }

  public CompanyPayload setLocation(String location) {
    this.location = location;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public CompanyPayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public CompanyPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<LeaderFrm> getLeaders() {
    return leaders;
  }

  public CompanyPayload setLeaders(List<LeaderFrm> leaders) {
    this.leaders = leaders;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public CompanyPayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public CompanyPayload setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public CompanyPayload setFax(String fax) {
    this.fax = fax;
    return this;
  }
}
