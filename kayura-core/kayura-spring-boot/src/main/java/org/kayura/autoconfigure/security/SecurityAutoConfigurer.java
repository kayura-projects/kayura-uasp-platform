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
