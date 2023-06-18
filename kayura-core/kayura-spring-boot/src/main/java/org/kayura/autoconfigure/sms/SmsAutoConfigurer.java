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

import org.kayura.security.sms.*;
import org.kayura.utils.CollectionUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({SmsSettings.class})
public class SmsAutoConfigurer {

  @Autowired
  private SmsSettings smsSettings;

  @Bean
  @ConditionalOnMissingBean(SmsTemplate.class)
  public SmsTemplate smsTemplate() {
    return new DefaultSmsTemplate(smsSettings.getTemplates());
  }

  @Bean
  @ConditionalOnMissingBean(ProxySmsManager.class)
  public DefaultProxySmsManager proxySmsSender(SmsTemplate template,
                                               ObjectProvider<List<SmsSender>> sendersProvider) {

    List<SmsSender> smsSenders = new ArrayList<>();
    smsSenders.add(new ConsoleSmsSender(template));

    List<SmsSender> customSenders = sendersProvider.getIfAvailable();
    if (CollectionUtils.isNotEmpty(customSenders)) {
      smsSenders.addAll(customSenders);
    }

    DefaultProxySmsManager sender = new DefaultProxySmsManager(smsSenders, smsSettings.getSender(), smsSettings.getDirectChannels());
    return sender;
  }
}
