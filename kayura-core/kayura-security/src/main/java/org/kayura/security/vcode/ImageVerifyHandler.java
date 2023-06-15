package org.kayura.security.vcode;

import org.kayura.type.Result;

public interface ImageVerifyHandler {

  void setNeed(String sessionId);

  boolean isNeed(String sessionId);

  void clean(String sessionId);

  String build(String sessionId);

  Result verify(String sessionId, String vcode);
}
