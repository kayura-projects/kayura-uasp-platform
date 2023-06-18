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
