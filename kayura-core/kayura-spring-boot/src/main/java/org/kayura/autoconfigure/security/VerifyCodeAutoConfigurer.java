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
