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
