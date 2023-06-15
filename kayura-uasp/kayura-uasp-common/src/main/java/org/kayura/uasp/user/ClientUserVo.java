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
