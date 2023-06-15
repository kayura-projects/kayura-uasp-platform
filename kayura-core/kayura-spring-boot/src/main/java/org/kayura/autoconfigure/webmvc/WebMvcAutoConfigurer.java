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
