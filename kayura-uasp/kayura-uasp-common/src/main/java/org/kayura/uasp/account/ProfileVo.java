/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
