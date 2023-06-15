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
