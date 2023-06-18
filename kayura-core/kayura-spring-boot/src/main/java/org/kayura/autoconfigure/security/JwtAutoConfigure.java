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
