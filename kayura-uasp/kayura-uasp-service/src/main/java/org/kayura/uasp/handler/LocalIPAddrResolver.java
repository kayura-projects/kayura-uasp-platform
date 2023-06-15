package org.kayura.uasp.handler;

import org.kayura.uasp.ip.IPTypes;
import org.springframework.stereotype.Component;

@Component
public class LocalIPAddrResolver implements IPAddrResolver {

  @Override
  public IPResult handle(String ipAddr) {
    return IPResult.builder().setDesc(ipAddr).setType(IPTypes.LOCAL);
  }

}
