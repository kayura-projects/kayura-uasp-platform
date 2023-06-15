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
