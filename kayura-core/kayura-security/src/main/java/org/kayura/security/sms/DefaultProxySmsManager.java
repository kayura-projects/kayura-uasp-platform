package org.kayura.security.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * The type Proxy sms sender.
 */
public class DefaultProxySmsManager implements ProxySmsManager {

  private final Log logger = LogFactory.getLog(this.getClass());
  private final List<SmsSender> senders;
  private final String defaultSender;
  private final Map<String, String> directChannels;

  /**
   * Instantiates a new Proxy sms sender.
   *
   * @param senders        the senders
   * @param defaultSender  the active sender
   * @param directChannels the direct channels
   */
  public DefaultProxySmsManager(List<SmsSender> senders,
                                String defaultSender,
                                Map<String, String> directChannels) {
    this.senders = senders;
    this.defaultSender = defaultSender;
    this.directChannels = directChannels;
  }

  private SmsSender activatedSender(String templateId) {

    SmsSender sender = null;

    if (directChannels.containsKey(templateId)) {
      sender = findSender(directChannels.get(templateId));
    }
    if (sender == null) {
      sender = findSender(defaultSender);
    }
    if (sender == null) {
      logger.error("短信发送器不存在。activeSender: " + defaultSender);
    }

    return sender;
  }

  private SmsSender findSender(String platform) {

    for (SmsSender sender : senders) {
      if (sender.getPlatform().equalsIgnoreCase(platform)) {
        return sender;
      }
    }
    return null;
  }

  @Override
  public String getPlatform() {
    return defaultSender;
  }

  @Override
  public void sendSms(String templateId, List<String> mobiles, Map<String, Object> params) {

    SmsSender sender = activatedSender(templateId);
    if (sender != null) {
      sender.sendSms(templateId, mobiles, params);
    }

  }
}
