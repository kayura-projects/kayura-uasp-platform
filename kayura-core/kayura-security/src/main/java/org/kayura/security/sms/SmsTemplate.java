package org.kayura.security.sms;

import java.util.Map;

public interface SmsTemplate {
  String resolve(String templateId, Map<String, Object> params);
}
