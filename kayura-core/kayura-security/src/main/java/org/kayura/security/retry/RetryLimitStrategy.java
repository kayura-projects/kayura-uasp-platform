package org.kayura.security.retry;

import org.kayura.type.Result;

public interface RetryLimitStrategy {

  Result retryCheck(String userName);

  RetryResult write(String userName);

  void clean(String userName);

}
