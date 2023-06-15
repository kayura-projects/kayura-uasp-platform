package org.kayura.uasp.ip;

import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IpStoreBody {

  @NotBlank
  private String ipAddr;
  @NotNull(groups = Update.class)
  private String ipDesc;
  @NotNull(groups = Update.class)
  private IPTypes type;

  public String getIpAddr() {
    return ipAddr;
  }

  public IpStoreBody setIpAddr(String ipAddr) {
    this.ipAddr = ipAddr;
    return this;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public IpStoreBody setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
    return this;
  }

  public IPTypes getType() {
    return type;
  }

  public IpStoreBody setType(IPTypes type) {
    this.type = type;
    return this;
  }
}
