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
