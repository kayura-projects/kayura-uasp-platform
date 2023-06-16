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

package org.kayura.autoconfigure.event;

import org.kayura.annotation.Priority;
import org.kayura.event.*;
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
public class EventBusAutoConfigurer {

  @Bean
  @ConditionalOnMissingBean(EventRegistry.class)
  public EventRegistry defaultEventRegistry(
    ObjectProvider<List<IEventListener>> objectProvider
  ) {
    EventRegistry registry = new DefaultEventRegistry();
    List<IEventListener> handlers = objectProvider.getIfAvailable();
    if (CollectionUtils.isNotEmpty(handlers)) {
      handlers.stream().map(handler -> {
        int priority = Optional.ofNullable(handler.getClass().getAnnotation(Priority.class))
          .map(Priority::value)
          .orElse(0);
        return new EventWrapper(handler, priority);
      }).sorted().forEach(handler ->
        registry.register(getEventType(handler.getListener()), handler)
      );
    }
    return registry;
  }

  @Bean
  @ConditionalOnMissingBean(EventGateway.class)
  public EventGateway simpleEventBus(EventRegistry eventRegistry) {
    EventGateway eventGateway = new DefaultEventGateway(eventRegistry);
    return eventGateway;
  }

  @SuppressWarnings("unchecked")
  private Class<? extends Event> getEventType(IEventListener handler) {

    Object target = handler;
    if (AopUtils.isCglibProxy(handler)) {
      target = AopProxyUtils.getSingletonTarget(handler);
    }
    Type[] interfaces = Objects.requireNonNull(target).getClass().getGenericInterfaces();
    ParameterizedType parameterizedType = (ParameterizedType) interfaces[0];
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    return (Class<? extends Event>) actualTypeArguments[0];
  }
}
