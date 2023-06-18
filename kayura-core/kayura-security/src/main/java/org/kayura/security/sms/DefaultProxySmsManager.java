/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
