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
