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

package org.kayura.autoconfigure.cmd;

import org.kayura.annotation.Priority;
import org.kayura.annotation.Support;
import org.kayura.cmd.*;
import org.kayura.utils.CollectionUtils;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
public class CommandAutoConfigurer {

  @SuppressWarnings("rawtypes")
  @Bean
  @ConditionalOnMissingBean(CommandRegistry.class)
  public CommandRegistry defaultCommandRegistry(
    ObjectProvider<List<CommandHandler>> handlerProviders,
    ObjectProvider<List<CommandInterceptor>> interceptorProviders,
    ObjectProvider<List<CommandPlugin>> pluginProviders
  ) {

    CommandRegistry registry = new DefaultCommandRegistry();

    // Plugin
    List<CommandPlugin> plugins = pluginProviders.getIfAvailable();
    if (CollectionUtils.isNotEmpty(plugins)) {
      plugins.stream().map(plugin -> {
        int priority = Optional.ofNullable(plugin.getClass().getAnnotation(Priority.class))
          .map(Priority::value)
          .orElse(0);
        Class<?>[] suppers = Optional.ofNullable(plugin.getClass().getAnnotation(Support.class))
          .map(Support::value).orElse(null);
        return new CommandPluginWrapper(plugin, suppers, priority);
      }).sorted().forEach(registry::addPlugin);
    }

    // Interceptor
    List<CommandInterceptor> interceptors = interceptorProviders.getIfAvailable();
    if (CollectionUtils.isNotEmpty(interceptors)) {
      interceptors.stream().map(interceptor -> {
        int priority = Optional.ofNullable(interceptor.getClass().getAnnotation(Priority.class))
          .map(Priority::value)
          .orElse(0);
        Class<?>[] suppers = Optional.ofNullable(interceptor.getClass().getAnnotation(Support.class))
          .map(Support::value).orElse(null);
        return new CommandInterceptorWrapper(interceptor, suppers, priority);
      }).sorted().forEach(registry::addInterceptor);
    }

    // Handler
    List<CommandHandler> handlers = handlerProviders.getIfAvailable();
    if (CollectionUtils.isNotEmpty(handlers)) {
      handlers.stream().map(handler -> {
        int priority = Optional.ofNullable(handler.getClass().getAnnotation(Priority.class))
          .map(Priority::value)
          .orElse(0);
        return new CommandHandlerWrapper(handler, priority);
      }).sorted().forEach(handler ->
        registry.subscribe(getCommandType(handler.getHandler()), handler)
      );
    }

    return registry;
  }

  @Bean
  @ConditionalOnMissingBean(CommandBus.class)
  public CommandBus defaultCommandBus(CommandRegistry registry) {

    CommandBus commandBus = new DefaultCommandBus(registry);
    return commandBus;
  }

  @Bean
  @ConditionalOnMissingBean(CommandGateway.class)
  public CommandGateway defaultCommandGateway(CommandRegistry registry, CommandBus commandBus) {

    CommandGateway commandGateway = new DefaultCommandGateway(registry, commandBus);
    return commandGateway;
  }

  @SuppressWarnings("unchecked")
  private Class<? extends Command> getCommandType(CommandHandler handler) {

    Object target = handler;
    if (AopUtils.isCglibProxy(handler)) {
      target = AopProxyUtils.getSingletonTarget(handler);
    }
    Type[] interfaces = Objects.requireNonNull(target).getClass().getGenericInterfaces();
    ParameterizedType parameterizedType = (ParameterizedType) interfaces[0];
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    return (Class<? extends Command>) actualTypeArguments[0];
  }

}
