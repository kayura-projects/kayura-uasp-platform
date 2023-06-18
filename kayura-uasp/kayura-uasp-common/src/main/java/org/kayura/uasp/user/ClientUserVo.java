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

package org.kayura.uasp.user;

import java.time.LocalDate;

public class ClientUserVo {

  private String clientId;
  private String clientNo;
  private String secretKey;
  private String displayName;
  private String remark;
  private Boolean enabled;
  private LocalDate accountExpire;

  public static ClientUserVo create() {
    return new ClientUserVo();
  }

  public String getClientId() {
    return clientId;
  }

  public ClientUserVo setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public ClientUserVo setSecretKey(String secretKey) {
    this.secretKey = secretKey;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ClientUserVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public ClientUserVo setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public ClientUserVo setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public String getClientNo() {
    return clientNo;
  }

  public ClientUserVo setClientNo(String clientNo) {
    this.clientNo = clientNo;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ClientUserVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }
}
