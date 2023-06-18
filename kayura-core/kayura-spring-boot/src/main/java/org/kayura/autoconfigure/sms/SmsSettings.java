/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
