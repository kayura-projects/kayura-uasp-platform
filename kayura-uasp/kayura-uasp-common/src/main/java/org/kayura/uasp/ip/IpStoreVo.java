package org.kayura.uasp.ip;

import java.time.LocalDateTime;

public class IpStoreVo {

  private String ipAddr;
  private String ipDesc;
  private IPTypes type;
  private LocalDateTime updateTime;

  public String getIpAddr() {
    return ipAddr;
  }

  public IpStoreVo setIpAddr(String ipAddr) {
    this.ipAddr = ipAddr;
    return this;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public IpStoreVo setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
    return this;
  }

  public IPTypes getType() {
    return type;
  }

  public IpStoreVo setType(IPTypes type) {
    this.type = type;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public IpStoreVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}
