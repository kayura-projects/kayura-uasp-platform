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
