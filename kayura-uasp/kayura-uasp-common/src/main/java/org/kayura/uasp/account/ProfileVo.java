package org.kayura.uasp.account;

import org.kayura.type.UserTypes;

public class ProfileVo {

  private String userId;
  private String avatar;
  private String displayName;
  private String mobile;
  private UserTypes userType;
  private String tenantId;
  private String tenantName;
  private String companyId;
  private String companyName;
  private String departId;
  private String departName;

  public static ProfileVo create() {
    return new ProfileVo();
  }

  public String getUserId() {
    return userId;
  }

  public ProfileVo setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public ProfileVo setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ProfileVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public ProfileVo setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public ProfileVo setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ProfileVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public ProfileVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public ProfileVo setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public ProfileVo setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public ProfileVo setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public ProfileVo setDepartName(String departName) {
    this.departName = departName;
    return this;
  }
}
