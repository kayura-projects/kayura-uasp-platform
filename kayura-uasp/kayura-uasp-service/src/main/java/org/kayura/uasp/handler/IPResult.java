package org.kayura.uasp.handler;

import org.kayura.uasp.ip.IPTypes;

public class IPResult {

  private IPTypes type;
  private String desc;

  public static IPResult builder() {
    return new IPResult();
  }

  public IPTypes getType() {
    return type;
  }

  public IPResult setType(IPTypes type) {
    this.type = type;
    return this;
  }

  public String getDesc() {
    return desc;
  }

  public IPResult setDesc(String desc) {
    this.desc = desc;
    return this;
  }
}
