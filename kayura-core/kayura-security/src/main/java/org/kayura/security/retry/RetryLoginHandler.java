package org.kayura.security.retry;

import org.kayura.type.Result;

public interface RetryLoginHandler {

  Result retryCheck(String userId);

}
