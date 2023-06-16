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
