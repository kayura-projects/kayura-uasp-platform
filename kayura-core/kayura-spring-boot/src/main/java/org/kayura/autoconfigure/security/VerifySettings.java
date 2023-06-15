package org.kayura.autoconfigure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kayura.verify")
public class VerifySettings {

  private Integer expire = 300;

  public Integer getExpire() {
    return expire;
  }

  public VerifySettings setExpire(Integer expire) {
    this.expire = expire;
    return this;
  }
}
