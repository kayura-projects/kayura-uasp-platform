package org.kayura.security.retry;

public interface MessageResolver {
  String resolve(RetryResult result);
}
