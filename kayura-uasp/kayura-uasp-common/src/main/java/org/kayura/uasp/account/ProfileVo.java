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
