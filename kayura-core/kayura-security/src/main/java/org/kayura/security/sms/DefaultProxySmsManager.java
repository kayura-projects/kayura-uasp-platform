/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.security.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class DefaultProxySmsManager implements ProxySmsManager {

  private final Log logger = LogFactory.getLog(this.getClass());
  private final List<SmsSender> senders;
  private final String defaultSender;
  private final Map<String, String> directChannels;

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
