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
