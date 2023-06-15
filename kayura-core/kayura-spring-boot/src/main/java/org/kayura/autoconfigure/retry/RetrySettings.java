package org.kayura.autoconfigure.retry;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kayura.retry")
public class RetrySettings {

  private Integer[] policy = {};

  public Integer[] getPolicy() {
    return policy;
  }

  public RetrySettings setPolicy(Integer[] policy) {
    this.policy = policy;
    return this;
  }
}
