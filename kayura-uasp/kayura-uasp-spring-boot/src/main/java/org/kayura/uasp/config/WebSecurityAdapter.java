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

package org.kayura.uasp.config;

import org.kayura.autoconfigure.security.SecuritySettings;
import org.kayura.security.jwt.JwtAuthTokenFilter;
import org.kayura.security.web.SecurityExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties({SecuritySettings.class})
public class WebSecurityAdapter {

  private final SecuritySettings securitySettings;
  private final SecurityExceptionHandler securityExceptionHandler;
  private final JwtAuthTokenFilter jwtAuthTokenFilter;

  public WebSecurityAdapter(SecuritySettings securitySettings,
                            SecurityExceptionHandler securityExceptionHandler,
                            JwtAuthTokenFilter jwtAuthTokenFilter) {
    this.securitySettings = securitySettings;
    this.securityExceptionHandler = securityExceptionHandler;
    this.jwtAuthTokenFilter = jwtAuthTokenFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    return http.csrf().disable()
      .exceptionHandling()
      .authenticationEntryPoint(securityExceptionHandler)
      .accessDeniedHandler(securityExceptionHandler)
      .and().logout().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().anonymous()
      .and().authorizeHttpRequests()
      .antMatchers(securitySettings.getPermitUrls()).permitAll()
      .anyRequest().authenticated()
      .and().cors().configurationSource(corsConfigurationSource())
      .and().addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class)
      .headers().frameOptions().sameOrigin()
      .cacheControl().and().and().build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers(securitySettings.getIgnoringUrls());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(false);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.setMaxAge(36000L);
    config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
