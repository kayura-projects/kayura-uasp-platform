/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
