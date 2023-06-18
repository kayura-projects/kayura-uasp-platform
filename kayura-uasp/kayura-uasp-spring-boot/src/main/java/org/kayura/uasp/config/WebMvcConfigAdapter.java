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

package org.kayura.uasp.config;

import org.kayura.autoconfigure.security.SecuritySettings;
import org.kayura.convert.EnumConverterFactory;
import org.kayura.log.RequestAccessInterceptor;
import org.kayura.security.web.SecuredInterceptor;
import org.kayura.spring.json.JsonFilterReturnValueHandler;
import org.kayura.spring.web.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Map;

@Configuration
public class WebMvcConfigAdapter implements WebMvcConfigurer {

  private final ObjectMapper objectMapper;
  private final SecuritySettings securitySettings;
  private final SecuredInterceptor securedInterceptor;
  private final RequestAccessInterceptor requestAccessInterceptor;
  private final JsonFilterReturnValueHandler jsonFilterReturnValueHandler;

  public WebMvcConfigAdapter(ObjectMapper objectMapper,
                             SecuritySettings securitySettings,
                             SecuredInterceptor securedInterceptor,
                             RequestAccessInterceptor requestAccessInterceptor,
                             JsonFilterReturnValueHandler jsonFilterReturnValueHandler) {
    this.objectMapper = objectMapper;
    this.securitySettings = securitySettings;
    this.securedInterceptor = securedInterceptor;
    this.requestAccessInterceptor = requestAccessInterceptor;
    this.jsonFilterReturnValueHandler = jsonFilterReturnValueHandler;
  }

  @Bean
  public HandlerExceptionResolver webExceptionHandler() {
    CustomWebExceptionHandler handler = new CustomWebExceptionHandler();
    handler.setOrder(-1);
    handler.setObjectMapper(objectMapper);
    handler.setDebug(true);
    return handler;
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverterFactory(new EnumConverterFactory());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestAccessInterceptor);
    registry.addInterceptor(securedInterceptor).addPathPatterns(securitySettings.getPrivilegeUrls());
  }

  @Override
  public void addViewControllers(@NotNull ViewControllerRegistry registry) {
    registry.addViewController("/uasp").setViewName("forward:/uasp/index.html");
  }

  @Override
  public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
    for (Map.Entry<String, String> me : securitySettings.getResourceMaps().entrySet()) {
      registry.addResourceHandler(me.getKey()).addResourceLocations(me.getValue());
    }
    registry.addResourceHandler("/uasp/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/kayura-uasp-angular/")
      .resourceChain(false);
  }

  @Override
  public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    handlers.add(jsonFilterReturnValueHandler);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new PageClauseArgumentResolver());
    resolvers.add(new OrderByClauseArgumentResolver());
    resolvers.add(new LoginUserArgumentResolver());
    resolvers.add(new CommandArgumentResolver());
  }

}
