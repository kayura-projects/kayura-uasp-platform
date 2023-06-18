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
