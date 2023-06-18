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

import org.kayura.security.core.LoginSuccessHandler;
import org.kayura.security.core.LoginUserService;
import org.kayura.security.core.NoopLoginSuccessHandler;
import org.kayura.security.core.StaticLoginUserService;
import org.kayura.security.encoder.HighPasswordVerify;
import org.kayura.security.encoder.MediumPasswordGradeVerify;
import org.kayura.security.encoder.PasswordGradeVerify;
import org.kayura.security.encoder.SimplePasswordGradeVerify;
import org.kayura.security.retry.DefaultMessageResolver;
import org.kayura.security.retry.MessageResolver;
import org.kayura.security.utils.DefaultIpWhitelist;
import org.kayura.security.utils.IpWhitelist;
import org.kayura.security.web.SecurityExceptionHandler;
import org.kayura.security.web.SecurityExceptionHandlerDefault;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kayura.utils.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({SecuritySettings.class, JwtSettings.class})
public class SecurityAutoConfigurer {

  private final ObjectMapper objectMapper;
  private final SecuritySettings securitySettings;

  public SecurityAutoConfigurer(ObjectMapper objectMapper,
                                SecuritySettings securitySettings) {
    this.objectMapper = objectMapper;
    this.securitySettings = securitySettings;
  }

  @SuppressWarnings("deprecation")
  @Bean
  public PasswordEncoder bundlePasswordEncoder() {

    String idForEncode = "bcrypt";
    BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(idForEncode, cryptPasswordEncoder);
    encoders.put("md5", new MessageDigestPasswordEncoder("MD5"));
    encoders.put("scrypt", new SCryptPasswordEncoder());
    encoders.put("sha256", new StandardPasswordEncoder());
    encoders.put("noop", NoOpPasswordEncoder.getInstance());

    DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
    delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(cryptPasswordEncoder);
    return delegatingPasswordEncoder;
  }

  @Bean
  @ConditionalOnMissingBean
  public PasswordGradeVerify passwordGradeVerify() {
    return switch (securitySettings.getPwdLevel()) {
      case MEDIUM -> new MediumPasswordGradeVerify();
      case HIGH -> new HighPasswordVerify();
      default -> new SimplePasswordGradeVerify();
    };
  }

  @Bean
  @ConditionalOnMissingBean
  public IpWhitelist ipWhitelist() {
    DefaultIpWhitelist ipWhitelist = new DefaultIpWhitelist();
    String[] whitelist = securitySettings.getWhitelist();
    if (CollectionUtils.isNotEmpty(whitelist)) {
      for (String item : whitelist) {
        ipWhitelist.addToWhitelist(item);
      }
    }
    return ipWhitelist;
  }

  @Bean
  @ConditionalOnMissingBean
  public SecurityExceptionHandler securityExceptionHandler() {
    return new SecurityExceptionHandlerDefault(objectMapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public LoginSuccessHandler loginSuccessHandler() {
    return new NoopLoginSuccessHandler();
  }

  @Bean
  @ConditionalOnMissingBean
  public LoginUserService loadUserService() {
    return new StaticLoginUserService();
  }

  @Bean
  @ConditionalOnMissingBean
  public MessageResolver messageResolver() {
    return new DefaultMessageResolver();
  }

}
