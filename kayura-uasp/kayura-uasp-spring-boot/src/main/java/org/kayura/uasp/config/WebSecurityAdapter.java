/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
