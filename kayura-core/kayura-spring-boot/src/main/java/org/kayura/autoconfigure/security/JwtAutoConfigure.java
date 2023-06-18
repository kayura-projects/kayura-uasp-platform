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

import org.kayura.autoconfigure.cache.CacheSettings;
import org.kayura.security.core.LoginSuccessHandler;
import org.kayura.security.core.LoginUserService;
import org.kayura.security.jwt.JwtAuthTokenFilter;
import org.kayura.security.jwt.JwtTokenUtil;
import org.kayura.security.web.SecurityExceptionHandler;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SecurityAutoConfigurer.class)
public class JwtAutoConfigure {

  private final JwtSettings jwtSettings;
  private final CacheManager cacheManager;
  private final LoginUserService loadUserService;
  private final LoginSuccessHandler loginSuccessHandler;
  private final SecurityExceptionHandler securityExceptionHandler;

  public JwtAutoConfigure(JwtSettings jwtSettings,
                          CacheManager cacheManager,
                          LoginUserService loadUserService,
                          LoginSuccessHandler loginSuccessHandler,
                          SecurityExceptionHandler securityExceptionHandler) {
    this.jwtSettings = jwtSettings;
    this.loadUserService = loadUserService;
    this.loginSuccessHandler = loginSuccessHandler;
    this.securityExceptionHandler = securityExceptionHandler;
    this.cacheManager = cacheManager;
  }

  @Bean
  public JwtTokenUtil jwtTokenUtil() {
    return new JwtTokenUtil(jwtSettings.getSecret(), jwtSettings.getExpire());
  }

  @Bean
  public JwtAuthTokenFilter jwtAuthTokenFilter() {
    Cache jwtCache = cacheManager.getCache(CacheSettings.JWT_TOKEN);
    JwtAuthTokenFilter jwtFilter = new JwtAuthTokenFilter(jwtTokenUtil(), jwtCache,
      loadUserService,
      loginSuccessHandler,
      jwtSettings.getPrefix(),
      jwtSettings.getHeader());
    jwtFilter.setAccessDeniedHandler(securityExceptionHandler);
    return jwtFilter;
  }

}
