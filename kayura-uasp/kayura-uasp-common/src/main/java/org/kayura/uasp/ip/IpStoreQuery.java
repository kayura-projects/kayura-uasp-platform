package org.kayura.uasp.ip;

import org.kayura.mybatis.annotation.querier.Like;

public class IpStoreQuery {

  private IPTypes type;
  @Like
  private String ipAddr;
  @Like
  private String ipDesc;

  public String getIpAddr() {
    return ipAddr;
  }

  public IpStoreQuery setIpAddr(String ipAddr) {
    this.ipAddr = ipAddr;
    return this;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public IpStoreQuery setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
    return this;
  }

  public IPTypes getType() {
    return type;
  }

  public IpStoreQuery setType(IPTypes type) {
    this.type = type;
    return this;
  }
}
