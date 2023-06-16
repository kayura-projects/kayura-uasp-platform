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

package org.kayura.autoconfigure.webmvc;

import org.kayura.autoconfigure.security.SecuritySettings;
import org.kayura.event.EventGateway;
import org.kayura.log.DefaultRequestAccessInterceptor;
import org.kayura.log.RequestAccessInterceptor;
import org.kayura.security.web.DefaultSecuredInterceptor;
import org.kayura.security.web.SecuredInterceptor;
import org.kayura.security.web.SecurityExceptionHandler;
import org.kayura.security.web.SecurityExceptionHandlerDefault;
import org.kayura.spring.json.JsonFilterReturnValueHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecuritySettings.class)
public class WebMvcAutoConfigurer {

  private final SecuritySettings securitySettings;
  private final ObjectMapper objectMapper;

  public WebMvcAutoConfigurer(SecuritySettings securitySettings,
                              ObjectMapper objectMapper) {
    this.securitySettings = securitySettings;
    this.objectMapper = objectMapper;
  }

  @Bean
  @ConditionalOnMissingBean(SecuredInterceptor.class)
  public SecuredInterceptor securedHandlerInterceptor(SecurityExceptionHandler securityExceptionHandler) {
    DefaultSecuredInterceptor interceptor = new DefaultSecuredInterceptor();
    interceptor.setAccessDeniedHandler(securityExceptionHandler);
    interceptor.setSecuredEnabled(securitySettings.isSecuredEnabled());
    return interceptor;
  }

  @Bean
  @ConditionalOnMissingBean(RequestAccessInterceptor.class)
  public RequestAccessInterceptor requestAccessInterceptor(EventGateway eventGateway) {
    RequestAccessInterceptor interceptor = new DefaultRequestAccessInterceptor(eventGateway);
    return interceptor;
  }

  @Bean
  @ConditionalOnMissingBean(SecurityExceptionHandler.class)
  public SecurityExceptionHandler securityExceptionHandler() {
    return new SecurityExceptionHandlerDefault(objectMapper);
  }

  @Bean
  @ConditionalOnMissingBean(JsonFilterReturnValueHandler.class)
  public JsonFilterReturnValueHandler jsonFilterReturnValueHandler() {
    return new JsonFilterReturnValueHandler(objectMapper);
  }

}
