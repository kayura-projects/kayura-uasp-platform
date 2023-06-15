package org.kayura.security.sms;

import java.util.List;
import java.util.Map;

/**
 * The interface Sms sender.
 */
public interface SmsSender {

  /**
   * Gets platform.
   *
   * @return the platform
   */
  String getPlatform();

  /**
   * Send sms.
   *
   * @param templateId the template id
   * @param mobiles    the mobiles
   * @param params     the params
   */
  void sendSms(String templateId, List<String> mobiles, Map<String, Object> params);
}