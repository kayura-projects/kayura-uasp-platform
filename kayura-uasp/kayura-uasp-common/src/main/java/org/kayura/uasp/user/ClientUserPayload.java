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
