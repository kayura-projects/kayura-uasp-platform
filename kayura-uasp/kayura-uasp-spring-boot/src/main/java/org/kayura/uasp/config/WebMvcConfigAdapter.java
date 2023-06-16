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
