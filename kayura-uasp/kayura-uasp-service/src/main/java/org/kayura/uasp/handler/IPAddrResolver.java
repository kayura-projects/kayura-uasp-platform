package org.kayura.uasp.handler;

/**
 * 调用外部网址，传入IP，转换为名称。
 */
public interface IPAddrResolver {
  IPResult handle(String ipAddr);
}
