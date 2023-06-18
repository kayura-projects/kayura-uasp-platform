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

import org.kayura.vaildation.CodeValid;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ClientUserPayload {

  @NotBlank(groups = Update.class)
  private String clientId;
  @NotBlank(groups = Create.class)
  @CodeValid
  private String clientNo;
  @NotBlank
  private String secretKey;
  @NotBlank
  private String displayName;
  @NotNull
  private Boolean enabled;
  private LocalDate accountExpire;
  private String remark;

  public String getSecretKey() {
    return secretKey;
  }

  public ClientUserPayload setSecretKey(String secretKey) {
    this.secretKey = secretKey;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ClientUserPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public ClientUserPayload setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public LocalDate getAccountExpire() {
    return accountExpire;
  }

  public ClientUserPayload setAccountExpire(LocalDate accountExpire) {
    this.accountExpire = accountExpire;
    return this;
  }

  public String getClientNo() {
    return clientNo;
  }

  public ClientUserPayload setClientNo(String clientNo) {
    this.clientNo = clientNo;
    return this;
  }

  public String getClientId() {
    return clientId;
  }

  public ClientUserPayload setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ClientUserPayload setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }
}
