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
