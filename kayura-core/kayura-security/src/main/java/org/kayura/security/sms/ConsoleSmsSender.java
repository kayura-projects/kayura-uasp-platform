package org.kayura.security.sms;

import org.kayura.utils.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * The type Console sms sender.
 */
public class ConsoleSmsSender implements SmsSender {

  private final SmsTemplate template;

  /**
   * Instantiates a new Console sms sender.
   *
   * @param template the template
   */
  public ConsoleSmsSender(SmsTemplate template) {
    this.template = template;
  }

  @Override
  public String getPlatform() {
    return "Console";
  }

  @Override
  public void sendSms(String templateId, List<String> mobiles, Map<String, Object> params) {

    String content = this.template.resolve(templateId, params);
    if (StringUtils.hasText(content)) {
      System.out.println("发送短信至：" + String.join(",", mobiles) + "。内容：" + content);
    }

  }

}
