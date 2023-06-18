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
