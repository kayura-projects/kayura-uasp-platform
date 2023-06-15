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
