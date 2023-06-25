/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
