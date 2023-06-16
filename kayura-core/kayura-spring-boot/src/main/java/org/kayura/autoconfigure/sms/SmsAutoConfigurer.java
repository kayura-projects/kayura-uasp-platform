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
