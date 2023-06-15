/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultEventRegistry implements EventRegistry {

  private final Map<Class<?>, CopyOnWriteArrayList<EventWrapper>> registry = new ConcurrentHashMap<>();

  @Override
  public <T extends IEvent> void register(Class<T> eventType, EventWrapper eventWrapper) {

    CopyOnWriteArrayList<EventWrapper> handlers =
      registry.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>());
    handlers.add(eventWrapper);
  }

  @Override
  public <T extends IEvent> void unregister(Class<T> eventType, IEventListener IEventListener) {
    if (registry.containsKey(eventType)) {
      registry.get(eventType).removeIf(wrapper -> IEventListener.equals(wrapper.getListener()));
    }
  }

  @Override
  public Map<Class<?>, CopyOnWriteArrayList<EventWrapper>> getRegistry() {
    return registry;
  }
}
