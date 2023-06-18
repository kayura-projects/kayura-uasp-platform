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

package org.kayura.autoconfigure.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "kayura.sms")
public class SmsSettings {

  private String sender;
  private Map<String, String> templates;
  private Map<String, String> directChannels;
  private boolean mock = false;

  public Map<String, String> getTemplates() {
    return templates;
  }

  public SmsSettings setTemplates(Map<String, String> templates) {
    this.templates = templates;
    return this;
  }

  public String getSender() {
    return sender;
  }

  public SmsSettings setSender(String sender) {
    this.sender = sender;
    return this;
  }

  public Map<String, String> getDirectChannels() {
    return directChannels;
  }

  public SmsSettings setDirectChannels(Map<String, String> directChannels) {
    this.directChannels = directChannels;
    return this;
  }

  public boolean isMock() {
    return mock;
  }

  public SmsSettings setMock(boolean mock) {
    this.mock = mock;
    return this;
  }
}
