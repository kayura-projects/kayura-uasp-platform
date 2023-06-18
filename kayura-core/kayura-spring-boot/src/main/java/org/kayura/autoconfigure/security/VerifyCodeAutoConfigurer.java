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

package org.kayura.autoconfigure.security;

import org.kayura.autoconfigure.sms.SmsAutoConfigurer;
import org.kayura.autoconfigure.sms.SmsSettings;
import org.kayura.security.sms.ProxySmsManager;
import org.kayura.security.vcode.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties({VerifySettings.class, SmsSettings.class})
@Import({SmsAutoConfigurer.class})
public class VerifyCodeAutoConfigurer {

  private final VerifySettings verifySettings;
  private final SmsSettings smsSettings;

  public VerifyCodeAutoConfigurer(VerifySettings verifySettings, SmsSettings smsSettings) {
    this.verifySettings = verifySettings;
    this.smsSettings = smsSettings;
  }

  @Bean
  @ConditionalOnMissingBean(ImageCodeBuilder.class)
  public ImageCodeBuilder imageCodeBuilder() {
    return new DefaultImageCodeBuilder();
  }

  @Bean
  @ConditionalOnMissingBean(ImageVerifyHandler.class)
  public ImageVerifyHandler imageVerifyCodeHandler(ImageCodeBuilder imageCodeBuilder,
                                                   CacheManager cacheManager
  ) {
    return new DefaultImageVerifyHandler(imageCodeBuilder, cacheManager, verifySettings.getExpire());
  }


  @Bean
  @ConditionalOnMissingBean(SmsVerifyHandler.class)
  public SmsVerifyHandler smsVerifyCodeHandler(ProxySmsManager proxySmsManager,
                                               CacheManager cacheManager) {
    return new DefaultSmsVerifyHandler(proxySmsManager, cacheManager, verifySettings.getExpire(), smsSettings.isMock());
  }

}
