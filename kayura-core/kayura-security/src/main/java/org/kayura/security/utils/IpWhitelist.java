package org.kayura.security.utils;

public interface IpWhitelist {
  boolean isAllowed(String ipAddress);
}
