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
