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
